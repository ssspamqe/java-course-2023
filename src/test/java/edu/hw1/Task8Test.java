package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    Task8 task = new Task8();
    @Test
    @DisplayName("Empty board must return true")
    void checkEmptyBoard(){

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

        assertThat(task.knightBoardCapture(board)).isEqualTo(true);
    }

    @Test
    @DisplayName("Random board (must return false)")
    void checkRandomBoard(){

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

        assertThat(task.knightBoardCapture(board)).isEqualTo(false);
    }

    @Test
    @DisplayName("Random board (must return true)")
    void checkRandomBoard1(){

        int[][] board = {
            {1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };

        assertThat(task.knightBoardCapture(board)).isEqualTo(true);
    }


    @Test
    @DisplayName("Random board (must return true)")
    void checkRandomBoard2(){

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

        assertThat(task.knightBoardCapture(board)).isEqualTo(true);
    }
}
