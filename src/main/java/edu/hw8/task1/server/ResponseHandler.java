package edu.hw8.task1.server;

import edu.hw8.task1.server.phraseDB.PhraseDB;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ResponseHandler {

    private static final PhraseDB PHRASE_DB = new PhraseDB();
    private static final int DEFAULT_BUFFER_CAPACITY = 1024;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final ByteBuffer byteBuffer;

    public ResponseHandler(int bufferCapacity) {
        byteBuffer = ByteBuffer.allocate(bufferCapacity);
    }

    public ResponseHandler() {
        this(DEFAULT_BUFFER_CAPACITY);
    }

    public String getAndSendPhrase(byte[] bytes, SocketChannel socketChannel) throws IOException, InterruptedException {
        String request = new String(bytes, DEFAULT_CHARSET).trim();

        Optional<String> possiblePhrase = PHRASE_DB.getPhrase(request);
        String phrase = possiblePhrase.orElse("No such phrase :(");

        sendResponse(phrase, socketChannel);
        return phrase;
    }

    private void sendResponse(String message, SocketChannel client) throws IOException {
        byteBuffer.clear()
            .put(message.getBytes())
            .flip();

        client.write(byteBuffer);
    }

}
