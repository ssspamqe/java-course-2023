package edu.project1;

import java.util.HashSet;
import java.util.Set;

class Session {
    private String word;
    private StringBuffer currentState;
    @SuppressWarnings("MagicNumber")
    private int maxMistakes = 5;
    private int mistakes = 0;

    private final Set<Character> playedChars = new HashSet<>();
    private int overallGuessed = 0;

    Dictionary dictionary = new Dictionary();

    Session() {
        word = dictionary.getRandomWord();
        currentState = new StringBuffer("*".repeat(word.length()));
    }

    Session(int maxMistakes) {
        this.maxMistakes = maxMistakes;
        word = dictionary.getRandomWord();
        currentState = new StringBuffer("*".repeat(word.length()));
    }

    public int tryGuess(char c) {
        playedChars.add(c);

        int guessedCnt = 0;
        for (int i = 0; i < currentState.length(); i++) {
            if (word.charAt(i) == c && currentState.charAt(i) == '*') {
                currentState.setCharAt(i, c);
                guessedCnt++;
            }
        }

        overallGuessed += guessedCnt;

        if (guessedCnt == 0) {
            mistakes++;
        }

        return guessedCnt;
    }

    public int getMaxMistakes() {
        return maxMistakes;
    }

    public int getMistakes() {
        return mistakes;
    }

    public String getCurrentState() {
        return currentState.toString();
    }

    public String getWord() {
        return word;
    }

    public boolean wasPlayed(char c) {
        return playedChars.contains(c);
    }

    public boolean won() {
        return overallGuessed == currentState.length();
    }
}
