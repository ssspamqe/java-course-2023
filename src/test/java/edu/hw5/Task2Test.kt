package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class Task2Test : ShouldSpec({

    val task = Task2()

    context("getAllFriday13th()") {
        should("return list of all fridays the 13th in the given year") {
            val year = 2000
            val correctList = listOf(LocalDate.of(2000, 10, 13))

            val result = task.getAllFriday13th(year)

            result shouldBe correctList
        }
    }

})
