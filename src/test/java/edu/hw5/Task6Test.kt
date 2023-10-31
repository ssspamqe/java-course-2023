package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task6Test:ShouldSpec({

    val task = Task6()

    context("containsString"){
        should("return true if superString contains another string"){
            val superString = "abacaba"
            val subString = "aca"

            val result = task.containsString(superString, subString)

            result shouldBe true
        }

        should("return false if superString do not contain another string"){
            val superString = "abacaba"
            val subString = "cc"

            val result = task.containsString(superString,subString)

            result shouldBe false
        }
    }

})
