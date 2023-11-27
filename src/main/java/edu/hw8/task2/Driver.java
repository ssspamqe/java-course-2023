package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int THREAD_POOL_SIZE = 5;
    private static final int MIN_N = 1;
    private static final int MAX_N = 40;

    private Driver() {

    }

    public static void launch() {
        try (MyThreadPool threadPool = MyThreadPool.create(THREAD_POOL_SIZE)) {
            for (int n = MIN_N; n <= MAX_N; n++) {
                int finalN = n;
                threadPool.execute(() ->
                    LOGGER.info("For n = {}: {}", finalN, getFibonacciNumber(finalN)));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static long getFibonacciNumber(long num) {
        if (num <= 1) {
            return num;
        }
        return getFibonacciNumber(num - 1) + getFibonacciNumber(num - 2);

    }
}
