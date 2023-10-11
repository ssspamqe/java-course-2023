package edu.hw2.Task3;

import edu.hw2.Task3.interfaces.Connection;
import edu.hw2.Task3.interfaces.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    Logger LOGGER = LogManager.getLogger();
    @Override
    public void execute(String command) throws ConnectionException {
        LOGGER.info("Executed command: " + command + " via FaultyConnection");
    }

    @Override
    public void close() throws Exception {

    }
}
