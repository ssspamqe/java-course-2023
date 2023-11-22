package edu.hw8.task1.server;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PhraseDB {

    private static final String DEFAULT_FILE_PATH = "./src/main/java/edu/hw8/task1/server/phrases.txt";

    private Map<String, List<String>> phrasesByWord = new HashMap<>();

    public PhraseDB(List<String> filePaths) {
        loadPhrases(filePaths);
    }

    public PhraseDB() {
        this(List.of(DEFAULT_FILE_PATH));
    }

    private void loadPhrases(List<String> filePaths) {
        for (var path : filePaths) {
            try (Scanner scanner = new Scanner(new FileInputStream(path))) {
                while (scanner.hasNextLine()) {
                    addNewPhrase(scanner.nextLine());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void addNewPhrase(String phrase) {
        var words = phrase.split(" ");
        for (var word : words) {
            if (!phrasesByWord.containsKey(word)) {
                phrasesByWord.put(word, new ArrayList<>());
            }
            phrasesByWord.get(word).add(phrase);
        }
    }

    public Optional<String> getPhrase(String word) {
        if (!phrasesByWord.containsKey(word)) {
            return Optional.empty();
        }
        var phrases = phrasesByWord.get(word);
        return Optional.of(getRandomElement(phrases));
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

}
