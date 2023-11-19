package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @CsvSource({"3456,235", "2,11", "361,14", "135,57", "126,131", "1361,167", "361,16", "48,1"})
    @DisplayName("increment async should increment number using several threads")
    void incrementAsync_should_incrementInt_usingSeveralThreads(int number, int threads) {
        int returnedNumber = Task1.incrementAsync(number, threads);

        assertThat(returnedNumber).isEqualTo(number + threads);
    }
}
