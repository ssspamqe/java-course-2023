package edu.hw3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Task2Test {

    val task = Task2();

    @Test
    @DisplayName("Method should split round bracket expressions into clusters")
    fun check_clusterizing() {
        val originalExpression = "((()))(())()()(()())";


        val clusterizedExpression = task.clusterize(originalExpression);


        assertThat(clusterizedExpression).isEqualTo(listOf("((()))", "(())", "()", "()", "(()())"))
    }

}
