package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.countK(7641)).isEqualTo(1);
    }

    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.countK(7641)).isEqualTo(1);
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.countK(3524)).isEqualTo(3);
    }
}
