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

    fun parseAllLogs(logs: List<String>): Table =
        Table(logs
            .map { parseLog(it) }
            .filter { it.isPresent }
            .map { it.get() })


    fun parseLog(log: String): Optional<Map<String, String>> {
        val regex =
            "(\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}) - (.+) \\[(.+)\\] \"(\\w+) (.+) (.+)\" (\\d+) (\\d+) \"(.+)\" \"(.+)\"".toRegex()
        if (!log.matches(regex))
            return Optional.empty()

        val capturedGroups = regex.findAll(log).toList()[0].groupValues

        val parsedLog = mutableMapOf<String, String>()

        for (i in 1 until capturedGroups.size)
            parsedLog[columnNames[i - 1]] = capturedGroups[i]

        return Optional.of(parsedLog)
    }


}
