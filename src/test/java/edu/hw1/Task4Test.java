package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    Task4 task = new Task4();
    @Test
    @DisplayName("Empty string must return empty string")
    void checkEmptyString(){
        assertThat(task.fixString("")).isEqualTo("");
    }

    @Test
    @DisplayName("String with length 1 must return itself")
    void checkStringWithLengthOne(){
        assertThat(task.fixString("5")).isEqualTo("5");
    }

    @Test
    @DisplayName("6587 must return 5678")
    void check6587(){
        assertThat(task.fixString("6587")).isEqualTo("5678");
    }

    @Test
    @DisplayName("String with odd length should return fixed string on s[:-1]")
    void checkStringWithOddLength(){
        assertThat(task.fixString("21435")).isEqualTo("12345");
    }
}
