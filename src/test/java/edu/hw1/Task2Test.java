package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Task2Test {
    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.countDigits(-90)).isEqualTo(2);
    }

    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.countDigits(754624)).isEqualTo(6);
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.countDigits(10)).isEqualTo(2);
    }

    @Test
    @DisplayName("5")
    void test5(){
        assertThat(Homework1.countDigits(9)).isEqualTo(1);
    }

}
