package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Task4Test: ShouldSpec({

    val task = Task4()

    context("validate password"){
        should("return true if password contains of os special symbols"){
            val password ="dsa^dd"

            val result = task.validatePassword(password)

            result shouldBe true
        }

        should("return false if password do not contain any of special symbols"){
            val password = "abacaba12"

            val result = task.validatePassword(password)

            result shouldBe false
        }
    }

})
