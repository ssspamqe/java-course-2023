package edu.project1;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Dictionary {

    private static final String DEFAULT_WORDS_PATH = "./src/main/java/edu/project1/words.txt";
    private static final String ONLY_ENGLISH_LETTERS_PATTERN = "[a-zA-z]+";

    private List<String> dictionary = List.of();

    public Dictionary(String filePath) {
        loadFile(filePath);
    }

    public Dictionary() {
        this(DEFAULT_WORDS_PATH);
    }

    public String getRandomWord() {
        return dictionary.get(ThreadLocalRandom.current().nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void addNewWord(String newWord) {
        if (!newWord.matches(ONLY_ENGLISH_LETTERS_PATTERN)) {
            throw new RuntimeException("Word must contain only english letters");
        }
        dictionary = addElement(dictionary, newWord.toLowerCase());
    }

    private <T> List<T> addElement(List<T> list, T newElement) {
        var bufArrayList = new ArrayList<>(list);
        bufArrayList.add(newElement);
        return bufArrayList.stream().toList();
    }

    private void loadFile(String filePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                addNewWord(scanner.nextLine());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
