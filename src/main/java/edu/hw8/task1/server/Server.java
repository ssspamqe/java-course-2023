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

    private boolean running = true;

    public void start() throws IOException, InterruptedException {
        try (Selector selector = Selector.open()) {
            LOGGER.info("opened selector");
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector)) {

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                while (running) {

                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                    while (keyIterator.hasNext() && !selectionKeys.isEmpty()) {
                        SelectionKey key = keyIterator.next();
                        executorService.submit(() -> {
                                try {
                                    handleSelectionKey(selector, key, serverSocket);
                                } catch (Exception ex){

                                }
                            }
                        );
                        LOGGER.info("deleting element from selectionKeys");
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
            ByteBuffer buffer = ByteBuffer.allocate(CLIENT_MESSAGE_CAPACITY);
            int bytesToRead;
            try {
                bytesToRead = client.read(buffer);
            } catch (Exception ex) {
                client.close();
                return;
            }
            LOGGER.info("From client: {}", new String(buffer.array(), StandardCharsets.UTF_8));
            if (bytesToRead == -1) {
                try {
                    client.close();
                } catch (Exception ex) {
                    return;
                }
            } else {
                LOGGER.info("sending response...");
                byte[] requestBytes = Arrays.copyOfRange(buffer.array(), 0, bytesToRead);
                try {
                    RESPONSE_HANDLER.getAndSendPhrase(requestBytes, client);
                } catch (Exception ex) {
                    return;
                }
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

}
