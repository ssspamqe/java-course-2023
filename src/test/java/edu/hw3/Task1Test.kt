package edu.hw3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Task1Test {

    val task = Task1()

    @Test
    @DisplayName("Method should \"inverse\" latin letters according to alphabet holding their case")
    fun check_latinLetters() {
        val originalString = "abcML"


        val encodedString = task.atbash(originalString)


        assertThat(encodedString).isEqualTo("zyxNO")
    }

    @Test
    @DisplayName("Method should not change non latin letters")
    fun check_nonLatinLetters() {
        val originalString = "тестAb"


        val encodedString = task.atbash(originalString)


        assertThat(encodedString).isEqualTo("тестZy")
    }
}
