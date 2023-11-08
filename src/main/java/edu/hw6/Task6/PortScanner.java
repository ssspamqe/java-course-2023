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

    PortScanner() {
        this(List.of(BASE_FILE_PATH));
    }

    PortScanner(List<String> filePaths) {
        this(filePaths, false);
    }

    PortScanner(List<String> filePaths, boolean includeBasicPorts) {
        if (includeBasicPorts) {
            addNewPorts(BASE_FILE_PATH);
        }
        filePaths.forEach(this::addNewPorts);
    }

    private void addNewPorts(String filePath) {

        Pattern linePattern = Pattern.compile("(\\d+) - ([^(?: \\- )]*)");

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

    public List<Map<String, String>> getOccupiedPorts() {

        List<Map<String, String>> occupiedPorts = new ArrayList<Map<String, String>>();

        for (int port = 0; port <= 49151; port++) {
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

    private Map<String, String> getPortInfo(int port, String protocol) {
        Map<String, String> portInfo = new HashMap<>();

        var service = portServices.get(port);
        if (service == null) {
            service = "???";
        }

        portInfo.put("protocol", protocol);
        portInfo.put("port", String.valueOf(port));
        portInfo.put("service", service);

        return portInfo;
    }

}
