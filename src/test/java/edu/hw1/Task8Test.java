package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    Task8 task = new Task8();
    @Test
    @DisplayName("Empty board must return true")
    void should_returnTrue_when_emptyBoard(){

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

        assertThat(task.knightBoardCapture(board)).isTrue();
    }

    @Test
    @DisplayName("Random board (must return false)")
    void should_returnFalse_when_oneKnightCanBeatAnother(){

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

        assertThat(task.knightBoardCapture(board)).isFalse();
    }

    @Test
    @DisplayName("Random board (must return true)")
    void should_returnFalse_when_oneKnightCantBeatAnother(){

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

        assertThat(task.knightBoardCapture(board)).isTrue();
    }
}
