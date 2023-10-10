package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task7Test {
    Task7 task = new Task7();
    @Test
    @DisplayName("right rotation of 8 by one is 4")
    void should_return4_when_rotating8By4Right(){
        assertThat(task.rotateRight(8,1)).isEqualTo(4);
    }

    @Test
    @DisplayName("right rotation of 35 by 2 is 56")
    void should_return56_when_rotating35By2Right(){
        assertThat(task.rotateRight(35,2)).isEqualTo(56);
    }


    @Test
    @DisplayName("left rotation of 223 by 8 is 223")
    void should_return233_when_rotating223By8Left(){
        assertThat(task.rotateLeft(223,8)).isEqualTo(223);
    }

    @Test
    @DisplayName("right rotation if 223 by 12 is 254")
    void should_return254_when_rotating223By12Right(){
        assertThat(task.rotateRight(223,12)).isEqualTo(253);
    }

    @Test
    @DisplayName("left rotation of 32 by 9 is 4")
    void should_return4_when_rotating32By9Left(){
        assertThat(task.rotateLeft(32,9)).isEqualTo(4);
    }

    @Test
    @DisplayName("left rotation of number by negative one is right rotation")
    void should_returnRightRotation_when_shiftToLeftRotationIsNegative(){
        assertThat(task.rotateLeft(567,-3)).isEqualTo(966);
    }

    @Test
    @DisplayName("n must be positive")
    void should_throwException_when_negativeNumber(){
        assertThatThrownBy(()->task.rotateLeft(-13241,1231)).isInstanceOf(IllegalArgumentException.class);
    }

}
