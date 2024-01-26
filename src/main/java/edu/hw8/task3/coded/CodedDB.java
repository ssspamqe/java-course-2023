package edu.hw8.task3.coded;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodedDB {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_DB_PATH = "./src/main/java/edu/hw8/task3/coded/coded.txt";

    private final Map<String, String> usersByHash = new HashMap<>();

    private CodedDB(String filePath) {
        loadFile(filePath);
    }

    private CodedDB() {
        this(DEFAULT_DB_PATH);
    }

    public void loadFile(String path) {
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
        lock.writeLock().lock();
        try {
            usersByHash.put(hash, user);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String remove(String key) {
        lock.writeLock().lock();
        try {
            return usersByHash.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean isEmpty() {
        lock.readLock().lock();
        try {
            return usersByHash.isEmpty();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static CodedDB getInstance() {
        return SingletonHolder.CODED_DB_INSTANCE;
    }

    private static class SingletonHolder {
        public static final CodedDB CODED_DB_INSTANCE = new CodedDB();
    }
}
