package edu.hw8.task1.server.phraseDB;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhraseDB {

    private static final String DEFAULT_FILE_PATH = "./src/main/java/edu/hw8/task1/server/phraseDB/phrases.txt";
    private static final Logger LOGGER = LogManager.getLogger();

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
            //TODO change to a more pleasant way
            String currentWord =
                word
                    .toLowerCase()
                    .replace(",", "")
                    .replace(".", "")
                    .replace("?","")
                    .replace("!","");
            if (!phrasesByWord.containsKey(word)) {
                phrasesByWord.put(currentWord, new ArrayList<>());
            }
            phrasesByWord.get(currentWord).add(phrase);
        }
    }

    public Optional<String> getPhrase(String word) throws InterruptedException {
        if (!phrasesByWord.containsKey(word)) {
            LOGGER.info("No phrase");
            return Optional.empty();
        }
        var phrases = phrasesByWord.get(word);
        var phrase = getRandomElement(phrases);
        LOGGER.info("found phrase: {}", phrase);

        //this.wait(750); //simulating work time
        return Optional.of(phrase);
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

}
