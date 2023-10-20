package edu.hw3

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Task4Test {

    val task = Task4()

    @Test
    @DisplayName("Method should convert arabic numbers into Roman ones")
    fun check_conversion() {
        val arabic = 3571


        val roman = task.convertToRoman(arabic)


        assertThat(roman).isEqualTo("MMMDLXXI")
    }

    @Test
    @DisplayName("Method should return \"I\" if num is 1 (border case)")
    fun check_one() {
        val arabic = 1;


        val roman = task.convertToRoman(arabic)


        assertThat(roman).isEqualTo("I")
    }

    @Test
    @DisplayName("Method should return \"IV\" if num is 4 (border case)")
    fun check_four() {
        val arabic = 4;


        val roman = task.convertToRoman(arabic)


        assertThat(roman).isEqualTo("IV")
    }

    @Test
    @DisplayName(
        "Method should throw Exception if number not in [1;3999] " +
            "(because is will be not possible to express number only with \"I\", \"V\", \"X\", \"L\", \"C\", \"D\", \"M\")"
    )
    fun check_range() {
        val arabic = 4000


        assertThatThrownBy { task.convertToRoman(arabic) }.isInstanceOf(IllegalArgumentException::class.java)
    }

}
