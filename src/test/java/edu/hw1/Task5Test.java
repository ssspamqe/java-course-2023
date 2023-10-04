package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.isPalindromeDescendant(11)).isEqualTo(true);
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.isPalindromeDescendant(11211230)).isEqualTo(true);
    }

    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.isPalindromeDescendant(567)).isEqualTo(false);
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.isPalindromeDescendant(23336014)).isEqualTo(true);
    }


}
