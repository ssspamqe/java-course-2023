package edu.hw5

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.hours
import kotlin.time.toKotlinDuration


class Task1 {

    public fun getStringAverageDuration(lines: List<String>): String {
        var totalDuration = 0.hours

        lines.forEach {
            totalDuration += getDuration(it)
        }

        totalDuration /= lines.size

        val hours = totalDuration.inWholeHours
        val minutes = totalDuration.inWholeMinutes - totalDuration.inWholeHours * 60;

        return "${hours}h ${minutes}m"
    }

    public fun getStringAverageDuration(vararg lines: String): String = getStringAverageDuration(lines.toList())

    private fun getDuration(line: String): kotlin.time.Duration {

        if (!line.matches("\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}".toRegex()))
            throw IllegalArgumentException("Illegal line")

        val borders = line.split(" - ")

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")

        val start = LocalDateTime.parse(borders[0], pattern)
        val end = LocalDateTime.parse(borders[1], pattern)

        return Duration.between(start, end).toKotlinDuration()
    }

}
