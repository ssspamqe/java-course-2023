package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    Task3 task = new Task3();

    @Test
    @DisplayName("Two equal arrays cant be nested")
    void checkEqualArrays() {

        int[] small = {1, 6, 12, 3, 6};
        int[] big = {1, 6, 12, 3, 6};

        assertThat(task.isNestable(small, big)).isEqualTo(false);
    }

    @Test
    @DisplayName("Array must be non empty")
    void checkEmptyArray() {

        int[] small = {};
        int[] big = {1, 6, 12, 3, 6};

        assertThatThrownBy(() -> task.isNestable(small, big)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Array may have int's maximal and minimal values")
    void checkIntegerMaxAndMIn() {

        int[] small = {-1, 8};
        int[] big = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        assertThat(task.isNestable(small, big)).isEqualTo(true);
    }

    @Test
    @DisplayName("Array with repeating elements should work")
    void checkRepeatingElements() {

        int[] small = {4, 5};
        int[] big = {4, 5, 5, 5};

        assertThat(task.isNestable(small, big)).isEqualTo(false);
    }

    @Test
    @DisplayName("Random input")
    void checkRandomInput() {

        int[] small = {4, 10};
        int[] big = {3, 5, 5, 10};

        assertThat(task.isNestable(small, big)).isEqualTo(false);
    }
}
