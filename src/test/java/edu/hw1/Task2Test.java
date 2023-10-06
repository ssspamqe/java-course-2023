package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Task2Test {

    Task2 task = new Task2();
    @Test
    @DisplayName("0 have 1 digit")
    void checkZero(){
        assertThat(task.countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Negative numbers have digits as their absolute value")
    void checkNegative(){
        assertThat(task.countDigits(-90)).isEqualTo(2);
    }

    @Test
    @DisplayName("754624 have 6 digits")
    void check754624(){
        assertThat(task.countDigits(754624)).isEqualTo(6);
    }

    @Test
    @DisplayName("10 have 2 digits")
    void check10(){
        assertThat(task.countDigits(10)).isEqualTo(2);
    }

    @Test
    @DisplayName("9 have one digit")
    void check9(){
        assertThat(task.countDigits(9)).isEqualTo(1);
    }

}
