package edu.hw8.task1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int DEFAULT_MESSAGE_CAPACITY = 1024;

    private final int port;
    private final ByteBuffer byteBuffer;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean connected = false;

    public Client(int port, int messageCapacity) {
        this.port = port;
        byteBuffer = ByteBuffer.allocate(messageCapacity);
    }

    public Client(int port) {
        this(port, DEFAULT_MESSAGE_CAPACITY);

    }

    public void start() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        connected = true;

        LOGGER.info("started user");
    }

    public void send(byte[] bytes) throws IOException {
        if(!connected)
            throw new RuntimeException("Not connected");

        byteBuffer.clear().put(bytes);
        byteBuffer.flip();
        LOGGER.info("writing to socker channel");
        socketChannel.write(byteBuffer);
    }

    public Optional<byte[]> waitResponse() throws IOException {
        if(!connected)
            throw new RuntimeException("Not connected");
        selector.select();
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        return Optional.of(byteBuffer.array());
    }

    public void close() throws IOException{
        if(connected){
            socketChannel.close();
            selector.close();
        }

    }
}
