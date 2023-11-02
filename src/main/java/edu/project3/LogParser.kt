package edu.project3

import java.util.Optional
import kotlin.math.log

class LogParser {

    fun parseAllLogs(logs: List<String>): List<Map<String, String>> =
        logs
            .map { parseLog(it) }
            .filter { it.isPresent }
            .map { it.get() }


    fun parseLog(log: String): Optional<Map<String, String>> {
        val regex =
            "(\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}) - (.+) \\[(.+)\\] \"(\\w+) (.+)\" (\\d{1,}) (\\d{1,}) \"(.+)\" \"(.+)\"".toRegex()
        if (!log.matches(regex))
            return Optional.empty()

        val capturedGroups = regex.findAll(log).toList()[0].groupValues

        val parsedLog = mutableMapOf<String, String>()

        val columnNames = listOf(
            "",
            "remote_addr",
            "remote_user",
            "time_local",
            "request_type",
            "request",
            "status",
            "body_bytes_sent",
            "http_referer",
            "http_user_agent"
        )

        for (i in 1 until capturedGroups.size)
            parsedLog[columnNames[i]] = capturedGroups[i]

        return Optional.of(parsedLog)
    }


}
