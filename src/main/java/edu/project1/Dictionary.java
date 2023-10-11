package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Dictionary {
    private static final List<String> dictionary = new ArrayList<>(List.of("machine", "house", "theatre"));
    private final Random rnd = new Random();

    public String getRandomWord() {
        return dictionary.get(rnd.nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public static void addNewWord(String newWord) {
        for (int i = 0; i < newWord.length(); i++) {
            if (!Character.isLetter(newWord.charAt(i)) || !Character.isLowerCase(newWord.charAt(i))) {
                throw new IllegalArgumentException("Word must contain only lower english letters");
            }
        }
        dictionary.add(newWord);
    }
}
