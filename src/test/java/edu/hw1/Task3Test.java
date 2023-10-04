package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("1")
    void test1(){

        int[] small = {1,6,12,3,6};
        int[] big = {1,6,12,3,6};

        assertThat(Homework1.isNestable(small,big)).isEqualTo(false);
    }

    @Test
    @DisplayName("2")
    void test2(){

        int[] small = {};
        int[] big = {1,6,12,3,6};

        assertThat(Homework1.isNestable(small,big)).isEqualTo(false);
    }

    @Test
    @DisplayName("3")
    void test3(){

        int[] small = {-1,8};
        int[] big = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        assertThat(Homework1.isNestable(small,big)).isEqualTo(true);
    }

    @Test
    @DisplayName("4")
    void test4(){

        int[] small = {4,5};
        int[] big = {4,5,5,5};

        assertThat(Homework1.isNestable(small,big)).isEqualTo(false);
    }

    @Test
    @DisplayName("5")
    void test5(){

        int[] small = {4,10};
        int[] big = {3,5,5,10};

        assertThat(Homework1.isNestable(small,big)).isEqualTo(false);
    }
}
