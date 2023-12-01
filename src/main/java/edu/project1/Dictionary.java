package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Dictionary {
    private List<String> dictionary = List.of("machine", "house", "theatre");

    public String getRandomWord() {
        return dictionary.get(ThreadLocalRandom.current().nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void addNewWord(String newWord) {
        String lowerCaseWord = newWord.toLowerCase();
        for (int i = 0; i < lowerCaseWord.length(); i++) {
            if (!Character.isLetter(lowerCaseWord.charAt(i))) {
                throw new IllegalArgumentException("Word must contain only lower english letters");
            }
        }
        dictionary = addElement(dictionary, lowerCaseWord);
    }

    private <T> List<T> addElement(List<T> list, T newElement) {
        var bufArrayList = new ArrayList<>(list);
        bufArrayList.add(newElement);
        return bufArrayList.stream().toList();
    }
}
