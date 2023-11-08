package edu.hw6.Task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Task6Driver {

    private static final PortScanner portScanner = new PortScanner();
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        var occupiedPorts = portScanner.getOccupiedPorts();
        printPorts(occupiedPorts);

    }

    private static void printPorts(List<Map<String, String>> ports) {
        LOGGER.info(String.format("%16s%16s%16s", "protocol", "port", "service"));

        ports.forEach(portInfo ->
            LOGGER.info(
                String.format(
                    "%16s%16s%16s",
                    portInfo.get("protocol"),
                    portInfo.get("port"),
                    portInfo.get("service")
                )
            )
        );
    }

}

