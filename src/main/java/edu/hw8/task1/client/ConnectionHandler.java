package edu.hw8.task1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int DEFAULT_MESSAGE_CAPACITY = 1024;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final int port;
    private final ByteBuffer byteBuffer;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean connected = false;

    public ConnectionHandler(int port, int messageCapacity) {
        this.port = port;
        byteBuffer = ByteBuffer.allocate(messageCapacity);
    }

    public ConnectionHandler(int port) {
        this(port, DEFAULT_MESSAGE_CAPACITY);
    }

    public void start() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        connected = true;
    }

    public void send(byte[] bytes) throws IOException {
        if (!connected) {
            throw new RuntimeException("Not connected");
        }

        byteBuffer
            .clear()
            .put(bytes)
            .flip();

        socketChannel.write(byteBuffer);
    }

    public String waitResponse() throws IOException {
        LOGGER.info("waiting server response...");
        if (!connected) {
            LOGGER.error("Connection troubles");
            return "no response";
        }
        selector.select();
        byteBuffer.clear();
        int bytesToRead = socketChannel.read(byteBuffer);
        byte[] responseBytes = Arrays.copyOfRange(byteBuffer.array(),0,bytesToRead);
        return new String(responseBytes, DEFAULT_CHARSET).trim();
    }

    public void close() throws IOException {
        if (connected) {
            socketChannel.close();
            selector.close();
        }

    }
}
