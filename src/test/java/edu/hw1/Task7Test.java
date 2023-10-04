package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("1")
    void test1(){
        assertThat(Homework1.rotateRight(8,1)).isEqualTo(4);
    }

    @Test
    @DisplayName("2")
    void test2(){
        assertThat(Homework1.rotateRight(35,2)).isEqualTo(56);
    }


    @Test
    @DisplayName("3")
    void test3(){
        assertThat(Homework1.rotateLeft(223,8)).isEqualTo(223);
    }

    @Test
    @DisplayName("4")
    void test4(){
        assertThat(Homework1.rotateRight(223,12)).isEqualTo(253);
    }

    @Test
    @DisplayName("5")
    void test5(){
        assertThat(Homework1.rotateLeft(32,9)).isEqualTo(4);
    }

}
