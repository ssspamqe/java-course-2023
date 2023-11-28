package edu.hw8.task3.coded;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodedDB {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_DB_PATH = "./src/main/java/edu/hw8/task3/coded/coded.txt";
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

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
                String[] parsedLine = parseLine(line);
                if (parsedLine != null) {
                    usersByHash.put(parsedLine[1], parsedLine[0]);
                }
            }
        } catch (IOException ex) {
            LOGGER.warn("File {} was not found", path);
        }
    }

    private String[] parseLine(String line) {
        String[] data = line.split(" ");
        if (data.length != 2) {
            LOGGER.warn("line \"{}\" is not correct", line);
            return null;
        }
        return data;
    }

    public void put(String hash, String user) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            usersByHash.put(hash, user);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public String remove(String key) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            return usersByHash.remove(key);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public boolean isEmpty() {
        READ_WRITE_LOCK.readLock().lock();
        try {
            return usersByHash.isEmpty();
        } finally {
            READ_WRITE_LOCK.readLock().unlock();
        }
    }
}
