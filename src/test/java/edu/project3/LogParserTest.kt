package edu.project3

import edu.project3.logWorkers.LogParser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import java.util.*

class LogParserTest : ShouldSpec({

    val parser = LogParser()

    context("parseLog()") {

        val log =
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""

        val incorrectLog =
            "93.180.71.0 - - [17/May/2015:08:05:32 +0000] \"/downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""

        val correctlyParsedLog = Optional.of(
            mapOf(
                "remote_addr" to "93.180.71.3",
                "remote_user" to "-",
                "time_local" to "17/May/2015:08:05:32 +0000",
                "request_type" to "GET",
                "request" to "/downloads/product_1",
                "protocol" to "HTTP/1.1",
                "status" to "304",
                "body_bytes_sent" to "0",
                "http_referer" to "-",
                "http_user_agent" to "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            )
        )

        should("return an Optional instance of line of Table") {
            val result = parser.parseLog(log)

            result shouldBe correctlyParsedLog
        }

        should("return empty Optional if log is not NGINX") {
            val result = parser.parseLog(incorrectLog)

            result shouldBe Optional.empty()
        }
    }

    context("parseAllLogs()") {
        val notParsedLogs = listOf(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
            "93.60.71.5 - - [17/May/2015:08:05:32 +0000] \"POST /downloads/product_1 HTTP/1.1\" 200 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
        )

        val parsedLogs = Table(
            listOf(
                mapOf(
                    "remote_addr" to "93.180.71.3",
                    "remote_user" to "-",
                    "time_local" to "17/May/2015:08:05:32 +0000",
                    "request_type" to "GET",
                    "request" to "/downloads/product_1",
                    "protocol" to "HTTP/1.1",
                    "status" to "304",
                    "body_bytes_sent" to "0",
                    "http_referer" to "-",
                    "http_user_agent" to "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                ),
                mapOf(
                    "remote_addr" to "93.60.71.5",
                    "remote_user" to "-",
                    "time_local" to "17/May/2015:08:05:32 +0000",
                    "request_type" to "POST",
                    "request" to "/downloads/product_1",
                    "protocol" to "HTTP/1.1",
                    "status" to "200",
                    "body_bytes_sent" to "0",
                    "http_referer" to "-",
                    "http_user_agent" to "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                )
            )
        )

        should("return Table of parsed logs") {
            val result = parser.parseAllLogs(notParsedLogs)

            result shouldBe parsedLogs
        }
    }

    context("dateTimeRegex") {
        val regex = parser.dateTimeRegex
        val correctTimes = listOf(
            "04/Aug/2020:08:05:56 +0001",
            "04/May/2021:08:05:02 +0000",
            "28/Sep/2022:09:05:15 +0000",
            "04/Mar/2020:14:05:25 +0000",
            "15/May/1900:08:05:45 -0003",
            "21/Apr/2000:10:05:02 +0000",
        )
        val incorrectTimes = listOf(
            "04/may/2020:08:05:56 ",
            "6/Aug/2020:08:05:56 +0000",
            "04/Aug/2020:08:05:56  +0000",
            "04-Aug-2020:08:05:56 +0000",
            "04/a/2020:08:05:56 +0000",
            "04/Aug/2020-08:05:56 +0000",
        )

        should("match to the correct time strings") {
            val result = correctTimes.map { it.matches(regex) }

            result shouldNotContain false
        }

        should("do not match to the incorrect strings") {
            val result = incorrectTimes.map { it.matches(regex) }

            result shouldNotContain true
        }
    }

    context("logRegex") {
        val regex = parser.logRegex
        val correctLogs = listOf(
            "46.4.66.76 - - [04/May/2020:08:05:02 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"",
            "62.75.198.179 - - [17/May/2015:08:05:06 +0000] \"PATCH /downloads/product_2 HTTP/3\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "80.91.33.133 - - [21/May/2015:08:05:55 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 513 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "173.203.139.108 - - [17/May/2015:08:05:53 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 100 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "127.0.0.1 - - [23/May/2015:08:05:32 +0001] \"GET /downloads/product_2 HTTP/1.1\" 304 102 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"",
            "46.4.83.163 - - [17/May/2015:08:05:52 +0000] \"POST /rootFile HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "91.234.194.89 - - [06/Sep/2018:08:05:18 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "31.22.86.126 - - [02/May/2015:08:05:24 +0000] \"PUT /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "217.168.17.5 - - [03/Aug/2015:08:05:25 +0000] \"GET /bigSecret.txt HTTP/1.0\" 200 3301 \"-\" \"-\""
        )
        val incorrectLogs = listOf(
            "46.4.66.76 -  -  [04/May/2020:08:05:02 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"",
            "62.75.198.179 - - [o7/May/2015:08:05:06 +0000] \"PATCH /downloads/product_2 HTTP/3\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "80 - - [21/May/2015:08:05:55 +0000] \"GET /downloads/product_1 TEXT/1.1\" 404 513 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "173.203.139.108 - - [17/May/2015:08:05:53 +0000] \" /downloads/product_1 HTTP/1.1\" 304 100 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
            "127.0.0  - - [3/May/2015:08:05:32 +0001] \"GET /downloads/product_2 HTTP/1.1\" 304 102 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\""
        )

        should("match to the correct NGINX logs") {
            val result = correctLogs.map { it.matches(regex) }

            result shouldNotContain false
        }

        should("do not match to the incorrect NGINX logs") {
            val result = incorrectLogs.map { it.matches(regex) }

            result shouldNotContain true
        }
    }
})
