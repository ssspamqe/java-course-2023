package edu.project1;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Dictionary {
    //List.of returns immutable Collection, so we will be not able to add new words
    // (and i decided to let it be just private)
    private List<String> dictionary = List.of("machine", "house", "theatre");
    private final Random rnd = new Random();

    public String getRandomWord() {
        return dictionary.get(rnd.nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void addNewWord(String newWord) {
        for (int i = 0; i < newWord.length(); i++) {
            if (!Character.isLetter(newWord.charAt(i)) || !Character.isLowerCase(newWord.charAt(i))) {
                throw new IllegalArgumentException("Word must contain only lower english letters");
            }
        }

        dictionary = Stream.concat(dictionary.stream(), Stream.of(newWord)).collect(Collectors.toUnmodifiableList());
    }
}
