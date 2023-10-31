package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task8Test: ShouldSpec({

    val task = Task8()

    context("Check0"){
        should("return true if string have odd length"){
            val string = "011"

            val result = task.check0(string)

            result shouldBe true
        }

        should("return false if string have even length"){
            val string = "0111"

            val result = task.check0(string)

            result shouldBe false
        }
    }

    context("check1"){
        should("return true if string starts with 0 and have odd length"){
            val string = "011"

            val result = task.check1(string)

            result shouldBe true
        }

        should("return true if string starts with 1 and have even length"){
            val string = "1101"

            val result = task.check1(string)

            result shouldBe true
        }

        should("return false if string starts with 0 and have even length"){
            val string = "0000"

            val result = task.check1(string)

            result shouldBe false
        }

        should("return false if string starts with 1 and have odd length"){
            val string = "100"

            val result = task.check1(string)

            result shouldBe false
        }
    }

})
