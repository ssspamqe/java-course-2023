package edu.hw3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Task3Test {
    val task = Task3()

    @Test
    @DisplayName("Method should find frequency of strings in list")
    fun check_strings() {
        val list = listOf("код", "код", "код", "bug")


        val frequencyDict = task.freqDict(list)


        assertThat(frequencyDict).isEqualTo(mapOf("код" to 3, "bug" to 1))
    }

    @Test
    @DisplayName("Method should find frequency of integers in list")
    fun check_int() {
        val list = listOf(1, 1, 2, 2)


        val frequencyDict = task.freqDict(list)


        assertThat(frequencyDict).isEqualTo(mapOf(1 to 2, 2 to 2))
    }
}
