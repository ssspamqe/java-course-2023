package edu.hw5

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.util.*

class Task3Test:ShouldSpec({

    val task = Task3()

    context("parseDate()"){
        should("return Optional.of(date) if given 2020-10-10"){
            val date= "2020-10-10"
            val correctDate = Optional.of(LocalDate.of(2020,10,10))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given 2020-1-1"){
            val date = "2020-1-1"
            val correctDate = Optional.of(LocalDate.of(2020,1,1))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if give 10/11/2020"){
            val date = "10/11/2020"
            val correctDate = Optional.of(LocalDate.of(2020,11,10))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given 1/1/2020"){
            val date = "1/1/2020"
            val correctDate = Optional.of(LocalDate.of(2020,1,1))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given tomorrow"){
            val date = "tomorrow"
            val correctDate = Optional.of(LocalDate.now().plusDays(1))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given today"){
            val date = "today"
            val correctDate = Optional.of(LocalDate.now())

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given yesterday"){
            val date = "yesterday"
            val correctDate = Optional.of(LocalDate.now().minusDays(1))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given \"n days ago\""){
            val date = "3 days ago"
            val correctDate = Optional.of(LocalDate.now().minusDays(3))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.of(date) if given \"1 day ago\""){
            val date ="1 day ago"
            val correctDate = Optional.of(LocalDate.now().minusDays(1))

            val result = task.parseDate(date)

            result shouldBe correctDate
        }

        should("return Optional.empty if non of the above options are given"){
            val date = "asa"
            val correctDate = Optional.empty<LocalDate>()

            val result = task.parseDate(date)

            result shouldBe correctDate
        }
    }

})
