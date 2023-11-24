package edu.hw8.task3.decryptors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiThreadDecrypter extends AbstractDecrypter {
    private ExecutorService threadPool;
    AtomicBoolean running = new AtomicBoolean(false);

    public Map<String, String> getDecryptedMap(int minLen, int maxLen, int nThreads) {
        return getDecryptedMap(List.of(), minLen, maxLen, nThreads);
    }

    public Map<String, String> getDecryptedMap(List<String> paths, int minLen, int maxLen, int nThreads) {
        if (minLen <= 0) {
            throw new IllegalArgumentException("minLen must be positive number");
        }

        loadDB(paths);

        decodedPasswords = new HashMap<>();
        threadPool = Executors.newFixedThreadPool(nThreads);
        running.set(true);

        var firstNumber = getFirstNumber(minLen, maxLen);

        for (int fistDigit = 1; fistDigit < alphabet.length() && running.get(); fistDigit++) {
            int finalFistDigit = fistDigit;
            threadPool.execute(() -> threadPoolTask(finalFistDigit, firstNumber));
        }

        threadPool.close();
        running.set(false);
        return decodedPasswords;
    }

    private void threadPoolTask(int firstDigit, int[] firstNumber) {
        var currentNumber = firstNumber.clone();
        currentNumber[0] = firstDigit;

        var nextNumber = Optional.of(currentNumber);

        while (nextNumber.isPresent() && running.get()) {
            currentNumber = nextNumber.get();
            String password = getPassword(currentNumber);
            handlePassword(password);
            if (fakeDB.isEmpty()) {
                running.set(false);
            }
            nextNumber = getNextNumber(currentNumber);
        }
    }
}
