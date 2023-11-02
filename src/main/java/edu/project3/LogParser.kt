package edu.project3

import java.util.Optional

class LogParser {

    fun parseLog(line:String):Optional<Map<String,String>>{
        val regex = "(\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}) - (.+) \\[(.+)\\] \"(\\w+) (.+)\" (\\d{1,}) (\\d{1,}) \"(.+)\" \"(.+)\"".toRegex()
        if(!line.matches(regex))
            return Optional.empty()

        val capturedGroups = regex.findAll(line).toList()[0].groupValues

        val logData = mutableMapOf<String,String>()

        val columnNames = listOf("",
            "remote_addr",
            "remote_user",
            "time_local",
            "request_type",
            "request",
            "status",
            "body_bytes_sent",
            "http_referer",
            "http_user_agent")

        for (i in 1 until capturedGroups.size)
            logData[columnNames[i]] = capturedGroups[i]

        return Optional.empty()
    }


}
