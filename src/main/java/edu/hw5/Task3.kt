package edu.hw5

import java.time.LocalDate
import java.util.*

class Task3 {

    public fun parseDate(line: String): Optional<LocalDate> =
        parseDashDate(line)


    private fun parseDashDate(line: String): Optional<LocalDate> {
        return try {
            Optional.of(LocalDate.parse(line))
        } catch (ex: Exception) {
            parseDashDateNoLeadingZeroes(line)
        }
    }

    private fun parseDashDateNoLeadingZeroes(line: String): Optional<LocalDate> {
        return try {
            if (!line.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}".toRegex()))
                throw Exception()

            val data = line.split('-').toMutableList()

            data[0] = data[0].addLeadingZeroes(4)
            data[1] = data[1].addLeadingZeroes(2)
            data[2] = data[2].addLeadingZeroes(2)

            Optional.of(LocalDate.parse(data.joinToString("-")))
        } catch (ex: Exception) {
            parseSlashDate(line)
        }
    }

    private fun parseSlashDate(line: String): Optional<LocalDate> {
        return try {

            if (!line.matches("\\d{1,2}/\\d{1,2}/\\d{1,4}".toRegex()))
                throw Exception()

            val data = line.split('/').toMutableList()

            data[0] = data[2].also { data[2] = data[0] }

            //можно такой колл вышестоящей в цепочке функции делать для предотвращения повторения строк?
            //просто мне кажется что это опасно (типа может бесконечный цикл получиться)
            parseDashDate(data.joinToString("-"))
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
            if (!line.matches("\\d+ (?:day)s? \\w+".toRegex()))
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
