package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {
    @SuppressWarnings("MemberName")
    private final Logger LOGGER = LogManager.getLogger();

    public void logHelloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
