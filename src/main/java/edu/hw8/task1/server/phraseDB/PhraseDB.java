package edu.hw8.task1.server.phraseDB;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhraseDB {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_FILE_PATH = "./src/main/java/edu/hw8/task1/server/phraseDB/phrases.txt";

    private final Map<String, List<String>> phrasesByWord = new HashMap<>();

    private PhraseDB() {
        this(DEFAULT_FILE_PATH);
    }

    private PhraseDB(String filePath) {
        loadFile(filePath);
    }

    public void loadFile(String filePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                addNewPhrase(scanner.nextLine());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewPhrase(String phrase) {
        var words = phrase.split(" ");
        for (var word : words) {
            String currentWord = getClearWord(word);
            if (currentWord.isBlank()) {
                continue;
            }

            lock.writeLock().lock();
            try {
                if (!phrasesByWord.containsKey(word)) {
                    phrasesByWord.put(currentWord, new ArrayList<>());
                }
                phrasesByWord.get(currentWord).add(phrase);
            } finally {
                lock.writeLock().unlock();
            }

        }
    }

    private String getClearWord(String word) {
        int start = -1;
        int end = -1;

        for (int i = 0; i < word.length(); i++) {
            boolean isAlphabeticChar = Character.isAlphabetic(word.charAt(i));
            if (isAlphabeticChar && start == -1) {
                start = i;
            }
            if (start != -1 && !isAlphabeticChar) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            end = word.length();
        }

        if (start == -1) {
            return "";
        }

        return word.substring(start, end).toLowerCase();
    }

    public Optional<String> getPhrase(String word) {
        List<String> phrases;
        lock.readLock().lock();
        try {
            phrases = phrasesByWord.get(word.toLowerCase());
        } finally {
            lock.readLock().unlock();
        }

        if (phrases == null) {
            LOGGER.info("No phrase with word '{}'", word);
            return Optional.empty();
        }

        var phrase = getRandomElement(phrases);
        LOGGER.info("found phrase: {}", phrase);

        return Optional.of(phrase);
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

    public static PhraseDB getInstance() {
        return SingletonHolder.PHRASE_DB_INSTANCE;
    }

    private static class SingletonHolder {
        public static final PhraseDB PHRASE_DB_INSTANCE = new PhraseDB();
    }
}
