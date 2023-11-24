package edu.hw8.task1.server;

import java.io.IOException;

public class ServerDriver {

    private ServerDriver() {

    }

    public static void launch() throws IOException, InterruptedException {
        Server server = new Server();
        server.start();
    }

}
