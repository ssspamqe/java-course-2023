package edu.hw8.task1.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class RequestThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Semaphore semaphore;
//    private final ResponseHandler responseHandler;
//    private final ByteBuffer buffer;
    public RequestThread(Semaphore semaphore,int bufferCapacity){
        this.semaphore = semaphore;

    }

    public void run(){
//        try{
//            LOGGER.warn("Thread {} is waiting semaphore",threadId());
////            semaphore.acquire();
//        }
    }

    private void handleSelectionKey(
        Selector selector,
        SelectionKey key,
        ServerSocketChannel serverSocketChannel,
        ByteBuffer buffer
    )
        throws IOException, InterruptedException {
        if (key.isAcceptable()) {
            configureSocketChannel(selector, serverSocketChannel);
        }
        if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();

            int bytesToRead = client.read(buffer);
            LOGGER.info("From client: {}", new String(buffer.array(), StandardCharsets.UTF_8));
            if (bytesToRead == -1) {
                client.close();
            } else {
                LOGGER.info("sending response...");
                byte[] requestBytes = Arrays.copyOfRange(buffer.array(), 0, bytesToRead);
               // RESPONSE_HANDLER.getAndSendPhrase(requestBytes, client);
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

}
