package edu.hw8.task3.decryptors;

import java.util.HashMap;
import java.util.Map;

public class SingleThreadDecrypter extends AbstractDecrypter {

    public Map<String, String> getDecryptedMap(int minLen, int maxLen) {
        if (minLen <= 0) {
            throw new IllegalArgumentException("minLen must be positive number");
        }

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
