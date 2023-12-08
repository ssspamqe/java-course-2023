package edu.hw10.task2;

import edu.hw10.Task2.cachingWorkers.CacheProxy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {

    private static final Path CACHE_FILE = Path.of("./src/test/java/edu/hw10/task2/testFiles/cache.txt");
    private static final Pattern CACHE_LOG_PATTERN = Pattern.compile(".+\\[(\\d+)\\] -> \\[(\\d+)\\]");

    @Test
    @DisplayName("CacheProxy should create cache for method and write it to the file")
    void cacheProxy_should_createCacheForMethod_and_writeToFile() throws IOException {
        Files.deleteIfExists(CACHE_FILE);
        int[] inputs = {12, 13};
        int[] outputs = {377, 610};

        Calculator fibonacciCalculator = new FibonacciCalculator();

        Calculator proxyCalculator = (Calculator) CacheProxy.create(fibonacciCalculator, CACHE_FILE.toString());
        proxyCalculator.calculate(12);
        proxyCalculator.calculate(13);

        List<String> filesLines = Files.lines(CACHE_FILE).toList();

        assertTrue(isCorrectCacheLogLine(filesLines.get(0), inputs[0], outputs[0]));
        assertTrue(isCorrectCacheLogLine(filesLines.get(1), inputs[1], outputs[1]));
    }

    private boolean isCorrectCacheLogLine(String line, int input, int output) {
        Matcher matcher = CACHE_LOG_PATTERN.matcher(line);
        if (matcher.matches()) {
            int lineInput = Integer.parseInt(matcher.group(1));
            int lineOutput = Integer.parseInt(matcher.group(2));
            return lineInput == input && lineOutput == output;
        }
        return false;
    }
}
