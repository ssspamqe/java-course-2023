package edu.project3

import edu.project3.logWorkers.LogParser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.util.*

class LogParserTest : ShouldSpec({

    val parser = LogParser()

    context("parseLog()") {
        should("return an Optional instance of line of Table") {
            val log =
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
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


            val result = parser.parseLog(log)


            result shouldBe correctlyParsedLog
        }

        should("return empty Optional if log is not NGINX") {
            val log =
                "93.180.71.0 - - [17/May/2015:08:05:32 +0000] \"/downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""

            val result = parser.parseLog(log)

            result shouldBe Optional.empty()
        }
    }

    context("parseAllLogs()") {
        should("return Table of parsed logs") {

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


            val result = parser.parseAllLogs(notParsedLogs)


            result shouldBe parsedLogs
        }
    }
})
