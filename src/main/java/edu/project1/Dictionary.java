package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Dictionary {
    private static final ArrayList<String> dictionary = new ArrayList<>(List.of("machine", "house", "theatre"));
    private static final Random rnd = new Random();

    public static String getRandomWord() {
        return dictionary.get(rnd.nextInt(dictionary.size()));
    }

    public static boolean addNewWord(String newWord) {
        for (int i = 0; i < newWord.length(); i++) {
            if (newWord.charAt(i) > 'z' || newWord.charAt(i) < 'a') {
                return false;
            }

        }
        dictionary.add(newWord);
        return true;
    }
}
