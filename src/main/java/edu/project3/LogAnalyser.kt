package edu.project3

import java.net.InetAddress
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

class LogAnalyser {

    fun getRequestsAmount(logs: List<Map<String, String>>): Int =
        logs.size


    fun getTheMostPopularResources(
        logs: List<Map<String, String>>,
        amount: Int = Int.MAX_VALUE,
        request: String = "GET"
    ): Map<String?, Int> {
        val sortedLogs = logs
            .filter { it["request_type"] == request }
            .map { it["request"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }

        return sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .toMap()
    }


    fun getTheMostPopularStatuses(
        logs: List<Map<String, String>>,
        amount: Int = Int.MAX_VALUE,
    ): Map<String?, Int> {

        val sortedLogs = logs
            .map { it["status"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .reversed()

        return sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .toMap()
    }

    fun getAverageResponseSize(logs: List<Map<String, String>>): Double =
        logs
            .filter { it["body_bytes_sent"]!!.toDoubleOrNull() != null }
            .map { it["body_bytes_sent"]!!.toDouble() }
            .average()

    fun getTheMostHighLoadedDays(
        logs: List<Map<String, String>>,
        amount: Int = Int.MAX_VALUE
    ): Map<LocalDate, Int> {

        val sortedLogs = logs
            .map {
                LocalDateTime.parse(
                    it["time_local"],
                    DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
                )
                    .toLocalDate()
            }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .reversed()

        return sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .toMap()
    }

    fun getTheMostActiveUsers(
        logs: List<Map<String, String>>,
        amount: Int = Int.MAX_VALUE
    ): Map<InetAddress, Int> {

        val sortedLogs = logs
            .map { InetAddress.getByName(it["remote_addr"]) }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .reversed()

        return sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .toMap()
    }

    fun getDateConstrainedLogs(
        logs: List<Map<String, String>>,
        from: LocalDate? = null,
        to: LocalDate? = null
    ): List<Map<String, String>> {

        var constrainedLogs = logs;

        if(from != null)
            constrainedLogs = setFromDateConstraint(constrainedLogs,from)
        if(to!=null)
            constrainedLogs = setToDateConstraint(constrainedLogs,to)

        return constrainedLogs
    }

    private fun setFromDateConstraint(
        logs: List<Map<String, String>>,
        from: LocalDate
    ): List<Map<String, String>> =
        logs.filter {
            LocalDateTime.parse(
                it["time_local"],
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
            )
                .toLocalDate() >= from
        }

    private fun setToDateConstraint(
        logs: List<Map<String, String>>,
        to: LocalDate
    ): List<Map<String, String>> =
        logs.filter {
            LocalDateTime.parse(
                it["time_local"],
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
            )
                .toLocalDate() <= to
        }

}
