package edu.hw8.task1.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Scanner;

public class ClientDriver {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int SERVER_PORT = 1337;
//    private  Client client;
//    private String serverName;

    public static void main(String[] params) throws Exception{
        launch();
    }

    public static void launch() throws IOException {
        ConnectionHandler connectionHandler = new ConnectionHandler(SERVER_PORT);
        try{
            connectionHandler.start();
        } catch (Exception ex){
            LOGGER.error("Server is not online");
            System.exit(1);
        }
        interactWithServer(connectionHandler);
        LOGGER.warn("finished");
    }

    private static void interactWithServer(ConnectionHandler connectionHandler){
        LOGGER.info("You need to end the stdin (ctrl + D) to finish the interaction with server");
        try(Scanner scanner = new Scanner(System.in)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                connectionHandler.send(line.getBytes());
                LOGGER.info(connectionHandler.waitResponse());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
