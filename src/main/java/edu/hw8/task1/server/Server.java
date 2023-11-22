package edu.hw8.task1.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 1337;
    private static final int CLIENT_MESSAGE_CAPACITY = 1024;
    private boolean running = true;

    public void start() throws IOException {
        try (Selector selector = Selector.open()) {
            LOGGER.info("opened selector");
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector)) {
                ByteBuffer buffer = ByteBuffer.allocate(CLIENT_MESSAGE_CAPACITY);
                while (running) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        buffer.clear();
                        handleSelectionKey(selector, key, serverSocket, buffer);
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
        }
    }



    private void handleSelectionKey(Selector selector, SelectionKey key, ServerSocketChannel serverSocketChannel, ByteBuffer buffer)
        throws IOException {
        if(key.isAcceptable())
            configureSelectionKey(selector,key,serverSocketChannel,buffer);
        if (key.isReadable()) {
            LOGGER.info("reading key");
            SocketChannel client = (SocketChannel) key.channel();
            int r = client.read(buffer);
            if (r == -1) {
                client.close();
            } else {
                рфтвлу
            }
        }
    }

    private void handleMessage(byte[] message){

    }

    private void configureSelectionKey(Selector selector, SelectionKey key, ServerSocketChannel serverSocketChannel, ByteBuffer buffer)
        throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        LOGGER.info("accepted user client");
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
