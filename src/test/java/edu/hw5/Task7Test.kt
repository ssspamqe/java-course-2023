package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task7Test : ShouldSpec({

    val task = Task7()

    context("Third symbol is zero()") {
        should("return true if third symbol is zero") {
            val string = "01011"

            val result = task.thirdSymbolIsZero(string)

            result shouldBe true
        }

        should("return false if third symbol is not zero") {
            val string = "001000"

            val result = task.thirdSymbolIsZero(string)

            result shouldBe false
        }

        should("return false if string have length less than 3") {
            val string = "00"

            val result = task.thirdSymbolIsZero(string)

            result shouldBe false
        }
    }

    context("haveSameStartAndEnd()") {
        should("return true if string have the same start and end symbols") {
            val string = "1010101"

            val result = task.haveSameStartAndEnd(string)

            result shouldBe true
        }

        should("return  false if string have different start and end symbols") {
            val string = "101010110"

            val result = task.haveSameStartAndEnd(string)

            result shouldBe false
        }
    }

    context("haveLengthInRange13()") {
        should("return true if string.length is in range [1;3]") {
            val string = "111"

            val result = task.haveLengthInRange13(string)

            result shouldBe true
        }

        should("return false if string.length is not in range[1;3]") {
            val string = "10001"

            val result = task.haveLengthInRange13(string)

            result shouldBe false
        }
    }

})
