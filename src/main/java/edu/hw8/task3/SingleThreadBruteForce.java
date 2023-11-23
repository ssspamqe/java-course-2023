package edu.hw8.task3;

import edu.hw8.task3.coded.CodedDB;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleThreadBruteForce {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Map<String, String> decodedPasswords = new HashMap<>();
    private final CodedDB fakeDB = new CodedDB();

    private String alphabet = " ";
    private int countingSystem;

    public SingleThreadBruteForce() {
        loadAlphabet();
        countingSystem = alphabet.length();
    }

    private void loadAlphabet(String... specialCharacters) {
        StringBuilder stringBuilder = new StringBuilder(alphabet);
        for (int i = 0; i < 26; i++) {
            stringBuilder.append((char) ('a' + i));
            stringBuilder.append((char) ('A' + i));
        }
        stringBuilder.append("1234567890");
        Arrays.stream(specialCharacters).forEach(stringBuilder::append);

        alphabet = stringBuilder.toString();
    }

    public Map<String,String> getDecodedMap(int maxLen) {
        int countingSystem = alphabet.length();
        int[] currentNumber = new int[maxLen];
        currentNumber[0] = -1;
        var nextNumber = getNextNumber(currentNumber);

        while (nextNumber.isPresent()) {
            currentNumber = nextNumber.get();

            String password = getPassword(currentNumber);
            handlePassword(password);
            if (fakeDB.isEmpty()) {
                break;
            }

            nextNumber = getNextNumber(currentNumber);
        }

        return decodedPasswords;
    }

    private Optional<int[]> getNextNumber(int[] currentNumber) {
        int[] nextNumber = currentNumber.clone();
        int i = 0;
        while(i < nextNumber.length && nextNumber[i] >= countingSystem-1){
            nextNumber[i]=0;
            i++;
        }
        if (i == nextNumber.length) {
            return Optional.empty();
        }
        nextNumber[i]++;
        return Optional.of(nextNumber);
    }

    private String getPassword(int[] number) {
        StringBuilder password = new StringBuilder();
        for (int j : number) {
            if (j == 0) {
                break;
            }
            password.append(alphabet.charAt(j));
        }
        return password.toString().trim();
    }

    private void handlePassword(String password) {
        String hash = getMD5Hash(password);
        if (password.equals("password")) {
            LOGGER.info(10);
        }
        String removedUser = fakeDB.remove(hash);
        if (removedUser != null) {
            decodedPasswords.put(removedUser, password);
        }
    }

    private String getMD5Hash(String s) {
        //LOGGER.info("{} - {}",s,DigestUtils.md5Hex(s));
        return DigestUtils.md5Hex(s);
    }



}
