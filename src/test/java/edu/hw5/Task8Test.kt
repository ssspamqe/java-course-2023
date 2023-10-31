package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task8Test: ShouldSpec({

    val task = Task8()

    context("Check0"){
        should("return true if string have odd length"){
            val string = "011"

            val returned = task.check0(string)

            returned shouldBe true
        }

        should("return false if string have even length"){
            val string = "0111"

            val returned = task.check0(string)

            returned shouldBe false
        }
    }

    context("check1"){
        should("return true if string starts with 0 and have odd length"){
            val string = "011"

            val returned = task.check1(string)

            returned shouldBe true
        }

        should("return true if string starts with 1 and have even length"){
            val string = "1101"

            val returned = task.check1(string)

            returned shouldBe true
        }

        should("return false if string starts with 0 and have even length"){
            val string = "0000"

            val returned = task.check1(string)

            returned shouldBe false
        }

        should("return false if string starts with 1 and have odd length"){
            val string = "100"

            val returned = task.check1(string)

            returned shouldBe false
        }
    }

})
