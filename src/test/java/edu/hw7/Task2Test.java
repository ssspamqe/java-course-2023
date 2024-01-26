package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("getAsyncFactorial should return a factorial of number")
    void getAsyncFactorial_should_return_factorialOfNumber() {
        int number = 10;
        int factorial = 3628800;

        int returnedFactorial = Task2.getAsyncFactorial(number);

        assertThat(returnedFactorial).isEqualTo(factorial);
    }

    @Test
    @DisplayName("getAsyncFactorial should return 1 if given 0")
    void getAsyncFactorial_should_return_1_if_given0() {
        int number = 1;
        int factorial = 1;

        int returnedFactorial = Task2.getAsyncFactorial(number);

        assertThat(returnedFactorial).isEqualTo(factorial);
    }

}
