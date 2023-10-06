package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    Task7 task = new Task7();
    @Test
    @DisplayName("right rotation of 8 by one is 4")
    void checkR8_1(){
        assertThat(task.rotateRight(8,1)).isEqualTo(4);
    }

    @Test
    @DisplayName("right rotation of 35 by 2 is 56")
    void checkR35_2(){
        assertThat(task.rotateRight(35,2)).isEqualTo(56);
    }


    @Test
    @DisplayName("left rotation of 223 by 8 is 223")
    void checkL223_8(){
        assertThat(task.rotateLeft(223,8)).isEqualTo(223);
    }

    @Test
    @DisplayName("right rotation if 223 by 12 is 254")
    void checkR223_12(){
        assertThat(task.rotateRight(223,12)).isEqualTo(253);
    }

    @Test
    @DisplayName("left rotation of 32 by 9 is 4")
    void checkL32_9(){
        assertThat(task.rotateLeft(32,9)).isEqualTo(4);
    }

    @Test
    @DisplayName("left rotation of number by negative one is right rotation")
    void checkL567_m3(){
        assertThat(task.rotateLeft(567,-3)).isEqualTo(966);
    }

}
