package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task6Test : ShouldSpec({

    val task = Task6()

    context("containsString") {
        should("return true if superString contains another string") {
            val superString = "abacaba"
            val subString = "aca"

            val result = task.containsSubsequence(superString, subString)

            result shouldBe true
        }

        should("return true if string contains subSequence") {
            val string = "abacaba"
            val subSequence = "aaa"

            val result = task.containsSubsequence(string, subSequence)

            result shouldBe true
        }

        should("return false if superString do not contain another string") {
            val superString = "abacaba"
            val subString = "cc"

            val result = task.containsSubsequence(superString, subString)

            result shouldBe false
        }
    }

})
