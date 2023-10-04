package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.minutesToSeconds("00:00")).isEqualTo(0);
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.minutesToSeconds("00:60")).isEqualTo(-1);
    }

    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.minutesToSeconds("67:61")).isEqualTo(-1);
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.minutesToSeconds("67:61")).isEqualTo(-1);
    }
    @Test
    @DisplayName("5")
    void test5(){
        assertThat(Homework1.minutesToSeconds("-1:07")).isEqualTo(-1);
    }

    @Test
    @DisplayName("6")
    void test6(){
        assertThat(Homework1.minutesToSeconds("01:07")).isEqualTo(67);
    }
}
