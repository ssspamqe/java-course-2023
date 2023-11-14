package edu.hw6.Task6;

import java.io.File;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PortScanner {

    public static Map<Integer, String> portServices = new HashMap<>();
    private static final String BASE_FILE_PATH = "./src/main/java/edu/hw6/Task6/possiblyOccupiedPorts.txt";

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    public static final String PORT_LINE_PATTERN = "(\\d+) - ([^ ].*)";

    public PortScanner() {
        this(List.of(BASE_FILE_PATH));
    }

    public PortScanner(List<String> filePaths) {
        this(filePaths, false);
    }

    public PortScanner(List<String> filePaths, boolean includeBasicPorts) {
        if (includeBasicPorts) {
            addNewPorts(BASE_FILE_PATH);
        }
        filePaths.forEach(this::addNewPorts);
    }

    private void addNewPorts(String filePath) {
        Pattern linePattern = Pattern.compile(PORT_LINE_PATTERN);

        try (Scanner occupiedPortsFile = new Scanner(new File(filePath))) {
            while (occupiedPortsFile.hasNext()) {
                var line = occupiedPortsFile.nextLine();
                var matcher = linePattern.matcher(line);

                if (!matcher.matches()) {
                    continue;
                }

                portServices.put(Integer.parseInt(matcher.group(1)), matcher.group(2));
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PortInfo> getOccupiedPorts() {

        List<PortInfo> occupiedPorts = new ArrayList<>();

        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            try {
                var serverSocket = new ServerSocket(port);
                serverSocket.close();

            } catch (Exception ex) {
                occupiedPorts.add(getPortInfo(port, "TCP"));
            }

            try {
                var datagramSocket = new DatagramSocket(port);
                datagramSocket.close();

            } catch (Exception ex) {
                occupiedPorts.add(getPortInfo(port, "UDP"));
            }
        }

        return occupiedPorts;

    }

    private PortInfo getPortInfo(int port, String protocol) {
        var service = portServices.get(port);
        if (service == null) {
            service = "???";
        }

        return new PortInfo(protocol, port, service);
    }

}
