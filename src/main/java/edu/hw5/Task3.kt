package edu.hw5

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Task3 {

    public fun parseDate(line: String): Optional<LocalDate> =
        parseDashDate(line)


    private fun parseDashDate(line: String): Optional<LocalDate> {
        return try {

            if (!line.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}".toRegex()))
                throw Exception()

            val lengths = line
                .split("-")
                .stream()
                .map { it.length }
                .toList()

            val pattern = DateTimeFormatter
                .ofPattern("y".repeat(lengths[0]) + "-" + "M".repeat(lengths[1]) + "-" + "d".repeat(lengths[2]))

            Optional.of(LocalDate.parse(line, pattern))
        } catch (ex: Exception) {
            parseSlashDate(line)
        }
    }

    private fun parseSlashDate(line: String): Optional<LocalDate> {
        return try {

            if (!line.matches("\\d{1,2}/\\d{1,2}/\\d{1,4}".toRegex()))
                throw Exception()

            val lengths = line
                .split("/")
                .stream()
                .map { it.length }
                .toList()

            val pattern = DateTimeFormatter
                .ofPattern("d".repeat(lengths[0]) + "/" + "M".repeat(lengths[1]) + "/" + "y".repeat(lengths[2]))

            Optional.of(LocalDate.parse(line, pattern))
        } catch (ex: Exception) {
            parseOneWordDate(line)
        }
    }

    private fun parseOneWordDate(line: String): Optional<LocalDate> {

        return try {
            if (!line.matches("\\w*".toRegex()))
                throw Exception()

            when (line) {
                "tomorrow" -> Optional.of(LocalDate.now().plusDays(1))
                "today" -> Optional.of(LocalDate.now())
                "yesterday" -> Optional.of(LocalDate.now().minusDays(1))
                else -> throw Exception()
            }
        } catch (ex: Exception) {
            return parseNDaysAgo(line)
        }
    }

    private fun parseNDaysAgo(line: String): Optional<LocalDate> {
        return try {
            if (!line.matches("\\d+ (day)s? ago".toRegex()))
                throw Exception()

            val days = line.split(" ")[0].toLong()

            Optional.of(LocalDate.now().minusDays(days))
        } catch (ex: Exception) {
            return Optional.empty()
        }
    }


    private fun String.addLeadingZeroes(neededSize: Int): String {
        return this.padStart(neededSize, '0')
    }


}
