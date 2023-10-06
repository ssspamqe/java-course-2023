package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    Task5 task = new Task5();
    @Test
    @DisplayName("Palindrome number should return true")
    void checkPalindromeNumber(){
        assertThat(task.isPalindromeDescendant(11)).isEqualTo(true);
    }

    @Test
    @DisplayName("11211230 must return true")
    void check11211230(){
        assertThat(task.isPalindromeDescendant(11211230)).isEqualTo(true);
    }

    @Test
    @DisplayName("Number with odd length must return false")
    void checkNumberWithOddLength(){
        assertThat(task.isPalindromeDescendant(567)).isEqualTo(false);
    }

    @Test
    @DisplayName("23336014 must return true")
    void check23336014(){
        assertThat(task.isPalindromeDescendant(23336014)).isEqualTo(true);
    }


}
