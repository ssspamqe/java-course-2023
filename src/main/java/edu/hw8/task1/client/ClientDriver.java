package edu.hw8.task1.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientDriver {
    private static final Logger LOGGER = LogManager.getLogger();
//    private  Client client;
//    private String serverName;

    public static void main(String[] params) throws Exception{
        Client client = new Client(1337);
        client.start();
        client.send(new byte[]{'a','b'});
        LOGGER.info(client.waitResponse());
    }

}
