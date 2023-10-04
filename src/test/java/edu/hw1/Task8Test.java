package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("1")
    void test1(){

        int[][] board = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(Homework1.knightBoardCapture(board)).isEqualTo(true);
    }

    @Test
    @DisplayName("2")
    void test2(){

        int[][] board = {
            {1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(Homework1.knightBoardCapture(board)).isEqualTo(false);
    }

    @Test
    @DisplayName("3")
    void test3(){

        int[][] board = {
            {1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(Homework1.knightBoardCapture(board)).isEqualTo(false);
    }

    @Test
    @DisplayName("4")
    void test4(){

        int[][] board = {
            {1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(Homework1.knightBoardCapture(board)).isEqualTo(true);
    }

    @Test
    @DisplayName("5")
    void test5(){

        int[][] board = {
            {1,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(Homework1.knightBoardCapture(board)).isEqualTo(true);
    }
}
