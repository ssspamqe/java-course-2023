package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MultiThreadDecrypter MULTI_THREAD_DECRYPTER = new MultiThreadDecrypter();
    private static final SingleThreadDecrypter SINGLE_THREAD_DECRYPTER = new SingleThreadDecrypter();

    private static final int MIN_LEN = 1;
    private static final int MAX_LEN = 4;

    private static final int MIN_THREADS = 2;
    private static final int MAX_THREADS = 10;

    private static final int SIMULATIONS = 5;

    public static void main(String[] params) throws InterruptedException {
        launch();
    }

    public static void launch() {
        var singleThreadTime = getSingleThreadTime();
        LOGGER.info(singleThreadTime);

        for (int nThreads = MIN_THREADS; nThreads <= MAX_THREADS; nThreads++) {
            double multiThreadTime = getMultithreadTime(nThreads);
            LOGGER.info("{} - {}", nThreads, singleThreadTime / multiThreadTime);
        }
    }

    private static double getSingleThreadTime() {
        var start = System.nanoTime();
        for (int i = 0; i < SIMULATIONS; i++) {
            SINGLE_THREAD_DECRYPTER.getDecryptedMap(MIN_LEN, MAX_LEN);

        }
        var end = System.nanoTime();
        double nanoseconds = (double) (end - start) / SIMULATIONS;
        return convertNanosecondsToMilliseconds(nanoseconds);
    }

    private static double getMultithreadTime(int nThread) {
        var start = System.nanoTime();
        for (int i = 0; i < SIMULATIONS; i++) {
            MULTI_THREAD_DECRYPTER.getDecryptedMap(MIN_LEN, MAX_LEN, nThread);
            LOGGER.info(nThread);
        }
        var end = System.nanoTime();
        double nanoseconds = (double) (end - start) / SIMULATIONS;
        return convertNanosecondsToMilliseconds(nanoseconds);
    }

    @SuppressWarnings("MagicalNumber")
    private static double convertNanosecondsToMilliseconds(double nanoseconds) {
        return nanoseconds / 1_000_000;
    }
}
