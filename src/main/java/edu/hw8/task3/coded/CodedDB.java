package edu.hw8.task3.coded;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodedDB {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_DB_PATH = "./src/main/java/edu/hw8/task3/coded/coded.txt";
    private final Map<String, String> usersByHash = new HashMap<>();

    public CodedDB(List<String> paths) {
        loadDB(paths);
    }

    public CodedDB() {
        this(List.of(DEFAULT_DB_PATH));
    }

    private void loadDB(List<String> paths) {
        paths.forEach(this::loadFile);
    }

    private void loadFile(String path) {
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                handleLine(line);
            }
        } catch (IOException ex) {
            LOGGER.warn("File {} was not found", path);
        }
    }

    private void handleLine(String line) {
        String[] data = line.split(" ");
        try {
            usersByHash.put(data[1], data[0]);
        } catch (Exception ex) {
            LOGGER.warn("line \"{}\" is not correct", line);
        }
    }

    public String remove(String key){
        return usersByHash.remove(key);
    }

    public boolean isEmpty(){
        return usersByHash.isEmpty();
    }
}
