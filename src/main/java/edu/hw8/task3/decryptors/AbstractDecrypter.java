package edu.hw8.task3.decryptors;

import edu.hw8.task3.coded.CodedDB;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AbstractDecrypter {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected final CodedDB fakeDB;
    protected String alphabet = " ";
    protected final int countingSystem;
    protected Map<String, String> decodedPasswords;

    public AbstractDecrypter(List<String> filePaths) {
        fakeDB = new CodedDB(filePaths);
        loadAlphabet();
        countingSystem = alphabet.length();

    }

    public AbstractDecrypter(){
        fakeDB = new CodedDB();
        loadAlphabet();
        countingSystem = alphabet.length();
    }

    protected void loadAlphabet(String... specialCharacters) {
        StringBuilder stringBuilder = new StringBuilder(alphabet);
        for (int i = 0; i < 26; i++) {
            stringBuilder.append((char) ('a' + i));
            stringBuilder.append((char) ('A' + i));
        }
        stringBuilder.append("1234567890");
        Arrays.stream(specialCharacters).forEach(stringBuilder::append);

        alphabet = stringBuilder.toString();
    }

    protected Optional<int[]> getNextNumber(int[] currentNumber) {
        int[] nextNumber = currentNumber.clone();
        int i = 0;
        while (i < nextNumber.length && nextNumber[i] >= countingSystem - 1) {
            nextNumber[i] = 1;
            i++;
        }
        if (i == nextNumber.length) {
            return Optional.empty();
        }
        nextNumber[i]++;
        return Optional.of(nextNumber);
    }

    protected String getPassword(int[] number) {
        StringBuilder password = new StringBuilder();
        for (int j : number) {
            if (j == 0) {
                break;
            }
            password.append(alphabet.charAt(j));
        }
        return password.toString().trim();
    }

    protected void handlePassword(String password) {
        String hash = DigestUtils.md5Hex(password);
        String removedUser = fakeDB.remove(hash);
        if (removedUser != null) {
            decodedPasswords.put(removedUser, password);
        }
    }

    protected int[] getFirstNumber(int minLen, int maxLen) {
        if (minLen <= 0) {
            throw new IllegalArgumentException("minLen must be positive number");
        }
        int[] number = new int[maxLen];
        for (int i = 0; i < minLen; i++) {
            number[i] = 1;
        }
        return number;
    }
}
