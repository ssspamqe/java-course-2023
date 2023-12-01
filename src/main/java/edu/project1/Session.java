package edu.project1;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Session {

    private static final int ENGLISH_LETTERS_AMOUNT = 26;

    private int healthPoints = AsciiPictures.MAX_HEALTH_POINTS;

    private final String word;
    private final StringBuffer currentState;

    private Set<Character> remainingChars;
    private int overallGuessed = 0;

    Dictionary dictionary = new Dictionary();

    Session() {
        word = dictionary.getRandomWord();
        currentState = new StringBuffer("*".repeat(word.length()));
        fillRemainingChars();
    }

    public int tryGuess(char c) {
        if (!remainingChars.contains(c)) {
            throw new IllegalArgumentException("This character was already tried");
        }
        remainingChars.remove(c);

        int guessedCnt = 0;
        for (int i = 0; i < currentState.length(); i++) {
            if (word.charAt(i) == c) {
                currentState.setCharAt(i, c);
                guessedCnt++;
            }
        }

        overallGuessed += guessedCnt;

        if (guessedCnt == 0) {
            healthPoints--;
            guessedCnt = -1;
        }

        return guessedCnt;
    }

    public boolean wasTried(char c) {
        return !remainingChars.contains(c);
    }

    public boolean won() {
        return overallGuessed == currentState.length();
    }

    public boolean lost() {
        return healthPoints <= 0;
    }

    public boolean ended() {
        return won() || lost();
    }

    private void fillRemainingChars() {
        remainingChars = new TreeSet<>();
        for (int i = 0; i < ENGLISH_LETTERS_AMOUNT; i++) {
            remainingChars.add((char) ('a' + i));
        }
    }

    public int getHealthPoints() {
        return getHealthPoints();
    }

    public String getHangmanPicture() {
        return AsciiPictures.getHangmanPicture(healthPoints);
    }

    public String getCurrentState() {
        return currentState.toString();
    }

    public String getWord() {
        return word;
    }

    public List<Character> getRemainingChars() {
        return remainingChars.stream().toList();
    }
}
