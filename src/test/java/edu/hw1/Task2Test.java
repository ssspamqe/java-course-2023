package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    Task2 task = new Task2();

    @Test
    @DisplayName("0 have 1 digit")
    void should_return1_when_numberIs0() {
        assertThat(task.countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Negative numbers have digits as their absolute value")
    void should_returnAmountOfDigitsOfAbsoluteValue_when_numberIsNegative() {
        assertThat(task.countDigits(-90)).isEqualTo(2);
    }

    @Test
    @DisplayName("754624 have 6 digits")
    void should_return6_when_number_is754624() {
        assertThat(task.countDigits(754624)).isEqualTo(6);
    }

    @Test
    @DisplayName("10 have 2 digits")
    void should_return2_when_numberIs10() {
        assertThat(task.countDigits(10)).isEqualTo(2);
    }

    @Test
    @DisplayName("9 have one digit")
    void should_return1_when_absoluteValueOfANumberLessThan10() {
        assertThat(task.countDigits(9)).isEqualTo(1);
    }

}
