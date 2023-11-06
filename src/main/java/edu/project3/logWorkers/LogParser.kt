package edu.project3.logWorkers

import edu.project3.Table
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
        Table(logs
            .map { parseLog(it) }
            .filter { it.isPresent }
            .map { it.get() })


    fun parseLog(log: String): Optional<Map<String, String>> {

        if (!log.matches(logRegex))
            return Optional.empty()

        val capturedGroups = logRegex.findAll(log).toList()[0].groupValues

        val parsedLog = mutableMapOf<String, String>()

        for (i in 1 until capturedGroups.size)
            parsedLog[columnNames[i - 1]] = capturedGroups[i]

        return Optional.of(parsedLog)
    }


}
