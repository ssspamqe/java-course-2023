package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.fixString("")).isEqualTo("");
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.fixString("5")).isEqualTo("5");
    }

    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.fixString("6587")).isEqualTo("5678");
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.fixString("21435")).isEqualTo("12345");
    }
}
