package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Dictionary {
    private final ArrayList<String> dictionary = new ArrayList<>(List.of("machine", "house", "theatre"));
    private final Random rnd = new Random();

    public String getRandomWord() {
        return dictionary.get(rnd.nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void addNewWord(String newWord) {
        for (int i = 0; i < newWord.length(); i++) {
            if (newWord.charAt(i) > 'z' || newWord.charAt(i) < 'a') {
                throw new IllegalArgumentException("Word must contain only lower english letters");
            }
        }
        dictionary.add(newWord);
    }
}
