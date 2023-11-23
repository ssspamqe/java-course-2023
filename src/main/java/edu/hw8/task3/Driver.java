package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String[] params) {
        SingleThreadBruteForce bf = new SingleThreadBruteForce();
        var decodedMap = bf.getDecodedMap(6);
        LOGGER.info(decodedMap);

    }
}
