package edu.project3

import edu.project3.logWorkers.LogAnalyser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class LogAnalyserTest : ShouldSpec({

    val analyser = LogAnalyser()

    context("getTheMostPopularResources()") {

        val logs = Table(
            listOf(
                mapOf(
                    "request_type" to "GET",
                    "request" to "file1"
                ),
                mapOf(
                    "request_type" to "GET",
                    "request" to "file2"
                ),
                mapOf(
                    "request_type" to "GET",
                    "request" to "file1"
                )
            )
        )

        val sortedResources = Table(
            listOf(
                mapOf(
                    "resource" to "file1",
                    "value" to "2"
                ),
                mapOf(
                    "resource" to "file2",
                    "value" to "1"
                )
            )
        )

        should("return the Table of requested resources sorted descending by amount of requests") {
            val result = analyser.getTheMostPopularResources(logs)

            result shouldBe sortedResources
        }
    }

    context("getTheMostPopularStatuses()") {
        val logs = Table(
            listOf(
                mapOf("status" to "200"),
                mapOf("status" to "200"),
                mapOf("status" to "504")
            )
        )

        val sortedStatuses = Table(
            listOf(
                mapOf(
                    "status" to "200",
                    "responses" to "2"
                ),
                mapOf(
                    "status" to "504",
                    "responses" to "1"
                )
            )
        )

        should("return the table of statuses, sorted descending by requests") {
            val result = analyser.getTheMostPopularStatuses(logs)

            result shouldBe sortedStatuses
        }
    }

    context("getAverageResponseSize()") {
        val logs = Table(
            listOf(
                mapOf("body_bytes_sent" to "300"),
                mapOf("body_bytes_sent" to "600")
            )
        )

        val averageResponseSize = 450

        should("return an average response size") {
            val result = analyser.getAverageResponseSize(logs)

            result shouldBe averageResponseSize
        }
    }

    context("getTheMostHighLoadedDays()") {
        val logs = Table(
            listOf(
                mapOf("time_local" to "17/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "17/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "18/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "19/May/2015:08:05:32 +0000")
            )
        )

        val sortedDays = Table(
            listOf(
                mapOf(
                    "day" to "2015-05-17",
                    "requests" to "2"
                ),
                mapOf(
                    "day" to "2015-05-18",
                    "requests" to "1"
                ),
                mapOf(
                    "day" to "2015-05-19",
                    "requests" to "1"
                )
            )
        )

        should("return the table of days, sorted descending by requests") {
            val result = analyser.getTheMostHighLoadedDays(logs)

            result shouldBe sortedDays
        }
    }

    context("getTheMostActiveUsers()") {
        val logs = Table(
            listOf(
                mapOf("remote_addr" to "localhost"),
                mapOf("remote_addr" to "127.0.0.1"),
                mapOf("remote_addr" to "8.8.8.8"),
            )
        )

        val sortedUsers = Table(
            listOf(
                mapOf(
                    "user_ip" to "127.0.0.1",
                    "requests" to "2"
                ),
                mapOf(
                    "user_ip" to "8.8.8.8",
                    "requests" to "1"
                )
            )
        )

        should("return table of users, sorted descending order by requests") {
            val result = analyser.getTheMostActiveUsers(logs)

            result shouldBe sortedUsers
        }
    }

    context("getDateConstrainedLogs()") {

        val logs = Table(
            listOf(
                mapOf("time_local" to "17/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "18/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "19/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "20/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "21/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "22/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "23/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "24/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "25/May/2015:08:05:32 +0000")
            )
        )

        val from = LocalDate.of(2015, 5, 19)
        val to = LocalDate.of(2015, 5, 24)

        val constrainedLogs = Table(
            listOf(
                mapOf("time_local" to "19/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "20/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "21/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "22/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "23/May/2015:08:05:32 +0000"),
                mapOf("time_local" to "24/May/2015:08:05:32 +0000")
            )
        )

        should("return a table of logs in specified time interval") {
            val result = analyser.getDateConstrainedLogs(logs, from, to)

            result shouldBe constrainedLogs
        }
    }
})
