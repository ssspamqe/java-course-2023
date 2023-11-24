package edu.hw8.task3.decryptors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleThreadDecrypter extends AbstractDecrypter {

    public SingleThreadDecrypter() {
        super();
    }

    public Map<String, String> getDecryptedMap(int minLen, int maxLen) {
        return getDecryptedMap(List.of(), minLen, maxLen);
    }

    public Map<String, String> getDecryptedMap(List<String> paths, int minLen, int maxLen) {
        if (minLen <= 0) {
            throw new IllegalArgumentException("minLen must be positive number");
        }

        loadDB(paths);

        decodedPasswords = new HashMap<>();

        var currentNumber = getFirstNumber(minLen, maxLen);
        currentNumber[0]--;
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
}
