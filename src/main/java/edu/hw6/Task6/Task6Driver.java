package edu.hw6.Task6;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6Driver {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TABLE_FORMAT = "%16s%16s%16s";

    private static final String PROTOCOL_STRING = "protocol";
    private static final String PORT_STRING = "port";
    private static final String SERVICE_STRING = "service";

    private Task6Driver() {
    }

    public static void printPorts(List<PortInfo> ports) {
        LOGGER.info(
            String.format(
                TABLE_FORMAT,
                PROTOCOL_STRING,
                PORT_STRING,
                SERVICE_STRING
            )
        );

        ports.forEach(portInfo ->
            LOGGER.info(
                String.format(
                    TABLE_FORMAT,
                    portInfo.protocol(),
                    portInfo.port(),
                    portInfo.service()
                )
            )
        );
    }

}

