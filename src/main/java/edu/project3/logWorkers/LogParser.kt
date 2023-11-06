package edu.project3.logWorkers

import edu.project3.Table
import edu.project3.sources
import java.io.File
import java.net.URL
import java.util.*

class LogParser {

    val columnNames = listOf(
        "remote_addr",
        "remote_user",
        "time_local",
        "request_type",
        "request",
        "protocol",
        "status",
        "body_bytes_sent",
        "http_referer",
        "http_user_agent"
    )

    val dateTimeRegex = "\\d{2}/[A-Z][a-z]{2}/\\d{4}:\\d{2}:\\d{2}:\\d{2} [\\-|\\+]\\d{4}".toRegex()

    val logRegex =
        "(\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}) - ([^ ]+) \\[($dateTimeRegex)\\] \\\"(\\w+) {1}(.+) (HTTP/.+)\\\" (\\d+) (\\d+) \\\"(.+)\\\" \\\"(.+)\\\""
            .toRegex()

    fun parseAllLogs(logs: List<String>): Table =
        Table(logs.mapNotNull { parseLog(it) })


    fun parseLog(log: String): Map<String, String?>? {

        if (!log.matches(logRegex))
            return null

        val capturedGroups = logRegex.findAll(log).toList()[0].groupValues

        val parsedLog = mutableMapOf<String, String>()

        for (i in 1 until capturedGroups.size)
            parsedLog[columnNames[i - 1]] = capturedGroups[i]

        return parsedLog
    }

    public fun combineLogs(sources:List<String>): List<String> {
        val nonParsedSources = mutableListOf<String>()

        sources.forEach { src ->
            val urlRegex = "(?:http)s?:\\/\\/.*".toRegex()

            if (src.matches(urlRegex))
                nonParsedSources.addAll(URL(src).readText().split("\n"))
            else
                File(src).useLines {
                    nonParsedSources.addAll(it.toList())
                }
        }

        return nonParsedSources.toList()
    }


}
