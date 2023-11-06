package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task5Test : ShouldSpec({

    val task = Task5()

    context("validateCarPlate()") {
        should("return true if given string in russian-style car plate") {
            val string = "W111WW111"

            val result = task.validateCarPlate(string)

            result shouldBe true
        }

        should("return false if given string is not russian-style car plate") {
            val string = "000qQ111"

            val result = task.validateCarPlate(string)

            result shouldBe false
        }
    }

})
