package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task6Test {
    Task6 task = new Task6();

    @Test
    @DisplayName("6174 must return 0")
    void should_return0_when_numberIsKaprekarConstant() {
        assertThat(task.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("7641 must return 1")
    void should_return1_when_numberIs7641() {
        assertThat(task.countK(7641)).isEqualTo(1);
    }

    @Test
    @DisplayName("3524 must return 3")
    void should_return3_when_numberIs3524() {
        assertThat(task.countK(3524)).isEqualTo(3);
    }

    @Test
    @DisplayName("Number must be in range (1000,9999]")
    void should_throwException_when_numberIsNotInCorrectRange() {
        assertThatThrownBy(() -> task.countK(1000)).isInstanceOf(IllegalArgumentException.class);
    }
}
