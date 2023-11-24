package edu.hw8.task1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
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
    private ExecutorService threadPool;

    private volatile boolean running = true;

    public void start() throws IOException, InterruptedException {
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (Selector selector = Selector.open()) {
            ByteBuffer buffer = ByteBuffer.allocate(CLIENT_MESSAGE_CAPACITY);
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector)) {
                while (running) {
                    selector.select();
                    var selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext() && !selectionKeys.isEmpty()) {
                        SelectionKey key = keyIterator.next();
                        buffer.clear();
                        handleSelectionKey(selector, key, buffer, serverSocket);
                        keyIterator.remove();
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
            threadPool.close();
        }
    }

    private void handleSelectionKey(
        Selector selector,
        SelectionKey key,
        ByteBuffer buffer,
        ServerSocketChannel serverSocketChannel
    ) {
        if (key.isAcceptable()) {
            configureSocketChannel(selector, serverSocketChannel);
            LOGGER.info("Accepted user");
        }
        if (key.isReadable()) {
            handleReadableKey(key, buffer);
        }
    }

    private void handleReadableKey(SelectionKey key, ByteBuffer buffer) {
        SocketChannel client = (SocketChannel) key.channel();

        int bytesToRead;
        try {
            bytesToRead = client.read(buffer);
        } catch (Exception ex) {
            LOGGER.warn("Unable to read data from client");
            bytesToRead = -1;
        }

        if (bytesToRead == -1) {
            try {
                client.close();
            } catch (Exception ex) {
                LOGGER.warn("Unable to close clinet");
                throw new RuntimeException(ex);
            }
        } else {
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

    private void configureSocketChannel(
        Selector selector,
        ServerSocketChannel serverSocketChannel
    ) {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } catch (Exception ex) {
            LOGGER.error("Unable to accept client");
            throw new RuntimeException(ex);
        }
    }

    private ServerSocketChannel configureServerSocketChannel(Selector selector) {
        ServerSocketChannel serverSocket = null;
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(PORT));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception ex) {
            LOGGER.error("Unable to configure server socker channel");
            throw new RuntimeException(ex);
        }
        return serverSocket;
    }
}
