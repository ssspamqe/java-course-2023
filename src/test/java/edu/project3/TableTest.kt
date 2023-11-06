package edu.project3

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TableTest : ShouldSpec({


    context("getColumnLength()") {
        should("return a length if longest item in the all ") {
            val table = Table(
                listOf(
                    mapOf("column1" to "aba"),
                    mapOf("column1" to "abacabadabacaba"),
                )
            )

            val len = table.getColumnLength("column1")

            len shouldBe 15
        }
    }

    context("getAllColumnLengths()") {
        should("return a map of all columns with their lengths") {
            val table = Table(
                listOf(
                    mapOf("column1" to "aba"),
                    mapOf("column2" to "abacabadabacaba"),
                )
            )

            val correctLens = mapOf(
                "column1" to 7,
                "column2" to 15
            )


            val lens = table.getAllColumnLengths()


            lens shouldBe correctLens
        }
    }
})
