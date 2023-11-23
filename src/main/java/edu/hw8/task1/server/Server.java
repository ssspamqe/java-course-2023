package edu.hw8.task1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 1337;
    private static final int CLIENT_MESSAGE_CAPACITY = 1024;
    private static final ResponseHandler RESPONSE_HANDLER = new ResponseHandler(CLIENT_MESSAGE_CAPACITY);
    private static final int THREAD_POOL_SIZE = 5;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private volatile boolean running = true;

    public void start() throws IOException, InterruptedException {
        try (Selector selector = Selector.open()) {
            LOGGER.info("opened selector");
            ByteBuffer buffer = ByteBuffer.allocate(CLIENT_MESSAGE_CAPACITY);
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector)) {

                Set<SelectionKey> a = new HashSet<>();

                while (running) {

                    selector.select();
                    var selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext() && !selectionKeys.isEmpty()) {
                        SelectionKey key = keyIterator.next();
                        buffer.clear();
                        handleSelectionKey(selector, key, buffer, serverSocket);
                        keyIterator.remove();
                        LOGGER.info("deleted element from selectionKeys, now size is {}", selectionKeys.size());
                    }
                }
            }
            selector.keys()
                .forEach(selectionKey -> {
                    try {
                        selectionKey.channel().close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
        }
    }

    private void handleSelectionKey(
        Selector selector,
        SelectionKey key,
        ByteBuffer buffer,
        ServerSocketChannel serverSocketChannel
    ) throws IOException {
        if (key.isAcceptable()) {
            try {
                configureSocketChannel(selector, serverSocketChannel);
            } catch (Exception ex) {
                return;
            }
        }
        if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();

            int bytesToRead;

            bytesToRead = client.read(buffer);

            LOGGER.info("From client: {}", new String(buffer.array(), StandardCharsets.UTF_8));
            LOGGER.info("Bytes to read: {}", bytesToRead);
            if (bytesToRead == -1) {

                client.close();

            } else {
                LOGGER.info("sending response...");
                byte[] requestBytes = Arrays.copyOfRange(buffer.array(), 0, bytesToRead);

                threadPool.execute(() -> {
                    try {
                        RESPONSE_HANDLER.getAndSendPhrase(requestBytes, client);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        }
    }

    private void configureSocketChannel(
        Selector selector,
        ServerSocketChannel serverSocketChannel
    )
        throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        LOGGER.info("accepted client");
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private ServerSocketChannel configureServerSocketChannel(Selector selector) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(PORT));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        return serverSocket;
    }
    //TODO add close

}
