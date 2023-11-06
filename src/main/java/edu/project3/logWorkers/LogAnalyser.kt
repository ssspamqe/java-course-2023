package edu.project3.logWorkers

import edu.project3.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

class LogAnalyser {

    fun getRequestsAmount(@NotNull logs: Table): Int =
        logs.size


    fun getTheMostPopularResources(
        logs: Table,
        amount: Int = Int.MAX_VALUE,
        request: String = "GET"
    ): Table {
        val sortedLogs = logs.tableRows
            .filter { it["request_type"] != null }
            .filter { it["request_type"] == request }
            .map { it["request"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }

        return Table(sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .map { mapOf("resource" to it.first!!, "value" to it.second.toString()) })
    }


    fun getTheMostPopularStatuses(
        logs: Table,
        amount: Int = Int.MAX_VALUE,
    ): Table {
        val sortedLogs = logs.tableRows
            .filter { it["status"] != null }
            .map { it["status"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }

        return Table(sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .map { mapOf("status" to it.first!!, "responses" to it.second.toString()) }
            .toList())
    }

    fun getAverageResponseSize(logs: Table): Double =
        logs.tableRows
            .filter { it["body_bytes_sent"] != null }
            .map { it["body_bytes_sent"]!!.toDouble() }
            .average()

    fun getTheMostHighLoadedDays(
        logs: Table,
        amount: Int = Int.MAX_VALUE
    ): Table {
        val sortedLogs = logs.tableRows
            .filter { it["time_local"] != null }
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
            .sortedByDescending { it.second }


        return Table(sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .map { mapOf("day" to it.first!!.toString(), "requests" to it.second.toString()) }
            .toList())
    }

    fun getTheMostActiveUsers(
        logs: Table,
        amount: Int = Int.MAX_VALUE
    ): Table {
        val sortedLogs = logs.tableRows
            .filter { it["remote_addr"] != null }
            .map { it["remote_addr"] }
            .map {
                if (it == "localhost") "127.0.0.1"
                else it
            }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .reversed()

        return Table(sortedLogs
            .subList(0, min(sortedLogs.size, amount))
            .map { mapOf("user_ip" to it.first, "requests" to it.second.toString()) }
            .toList())
    }

    fun getDateConstrainedLogs(
        logs: Table,
        from: LocalDate? = null,
        to: LocalDate? = null
    ): Table {
        var constrainedLogs = logs

        if (from != null)
            constrainedLogs = setFromDateConstraint(constrainedLogs, from)
        if (to != null)
            constrainedLogs = setToDateConstraint(constrainedLogs, to)

        return constrainedLogs
    }

    private fun setFromDateConstraint(
        logs: Table,
        from: LocalDate
    ): Table =
        Table(
            logs.tableRows
                .filter { it["time_local"] != null }
                .filter {
                    LocalDateTime.parse(
                        it["time_local"],
                        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
                    )
                        .toLocalDate() >= from
                }
        )

    private fun setToDateConstraint(
        logs: Table,
        to: LocalDate
    ): Table =
        Table(
            logs.tableRows
                .filter { it["time_local"] != null }
                .filter {
                    LocalDateTime.parse(
                        it["time_local"],
                        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
                    )
                        .toLocalDate() <= to
                }
        )


}
