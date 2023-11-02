package edu.hw5
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task1Test:ShouldSpec({

    val task = Task1()

    context("getStringAverageDuration()"){
        should("should return average time spent in club"){
            val records = listOf("2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20")
            val correctStringAverageDuration = "3h 40m"

            val result = task.getStringAverageDuration(records)

            result shouldBe correctStringAverageDuration
        }
    }

})
