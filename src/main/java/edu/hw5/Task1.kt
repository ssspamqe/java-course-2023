package edu.hw5

import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.toKotlinDuration


class Task1 {

    public fun getStringAverageDuration(vararg lines: String):String {

        var totalDuration = 0.hours

        lines.forEach {
            totalDuration += getDuration(it)
        }

        totalDuration /= lines.size

        val hours  = totalDuration.inWholeHours
        val minutes = totalDuration.inWholeMinutes - totalDuration.inWholeHours * 60;

        return "${hours}h ${minutes}m"

    }

    public fun getDuration(line: String): kotlin.time.Duration {

        val checkingRegex = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}".toRegex()
        if (!checkingRegex.matches(line))
            throw IllegalArgumentException("Illegal line")

        val borders = line.split(" - ")

        val dateRegex = "(.*),".toRegex()
        val startDate = dateRegex.find(borders[0])!!.groupValues[1]
        val endDate = dateRegex.find(borders[1])!!.groupValues[1]

        val timeRegex = " (.*)".toRegex()
        val startTime = timeRegex.find(borders[0])!!.groupValues[1]
        val endTime = timeRegex.find(borders[1])!!.groupValues[1]

        val start = LocalDateTime.parse("${startDate}T${startTime}:00")
        val end = LocalDateTime.parse("${endDate}T${endTime}:00")

        return Duration.between(start, end).toKotlinDuration()


    }

}
