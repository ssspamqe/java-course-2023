package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private List<Character> lowers = new ArrayList<>();
    private List<Character> uppers = new ArrayList<>();
    private int lowersStart = 'a';
    private int uppersStart = 'A';

    private final static Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("MagicNumber")
    public Task1() {
        for (int i = 0; i < 26; i++) {
            lowers.add((char) (lowersStart + i));
            uppers.add((char) (uppersStart + i));
        }
    }

    @SuppressWarnings("MagicNumber")
    public String atbash(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (isLatinLowerLetter(s.charAt(i))) {
                res.append(lowers.get(25 - (s.charAt(i) - lowersStart)));
            } else if (isLatinUpperLetter(s.charAt(i))) {
                res.append(uppers.get(25 - (s.charAt(i) - uppersStart)));
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    //because Character.isLetter('ф') == true
    private boolean isLatinLowerLetter(char c) {
        return 'a' <= c && c <= 'z';
    }

    private boolean isLatinUpperLetter(char c) {
        return 'A' <= c && c <= 'Z';
    }

}
