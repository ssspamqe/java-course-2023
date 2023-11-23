package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String[] params) throws InterruptedException {
        {
            var start = System.nanoTime();
            MultiThreadDecrypter bf = new MultiThreadDecrypter();
            var decodedMap = bf.getDecodedMap(1,4,2);
            LOGGER.info(decodedMap);
            var end = System.nanoTime();
            LOGGER.error(end - start);
        }

        {
            var start = System.nanoTime();
            SingleThreadDecrypter bf = new SingleThreadDecrypter();
            var decodedMap = bf.getDecodedMap(1,4);
            LOGGER.info(decodedMap);
            var end = System.nanoTime();
            LOGGER.error(end - start);
        }
    }
}
