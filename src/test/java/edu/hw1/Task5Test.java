package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    Task5 task = new Task5();
    @Test
    @DisplayName("Palindrome number should return true")
    void should_returnTrue_when_numberIsPalindrome(){
        assertThat(task.isPalindromeDescendant(11)).isTrue();
    }

    @Test
    @DisplayName("11211230 must return true")
    void should_returnTrue_when_canGetPalindrome(){
        assertThat(task.isPalindromeDescendant(11211230)).isTrue();
    }

    @Test
    @DisplayName("Number with odd length must return false")
    void should_returnFalse_when_lengthOfNumberIsOdd(){
        assertThat(task.isPalindromeDescendant(567)).isFalse();
    }
}
