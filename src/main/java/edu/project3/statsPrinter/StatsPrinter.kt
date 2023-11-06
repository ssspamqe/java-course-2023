package edu.project3.statsPrinter

import edu.project3.Table
import edu.project3.logAnalyser
import edu.project3.tablePrinters.TablePrinter
import java.time.LocalDate

class StatsPrinter(private val tablePrinter: TablePrinter) {
    fun printOverallInfo(
        logs: Table,
        sources: List<String>,
        from: LocalDate?,
        to: LocalDate?,
    ) {
        val table = Table(
            listOf(
                mapOf(
                    "metrics" to "Files",
                    "value" to sources.toString()
                ),
                mapOf(
                    "metrics" to "Start date",
                    "value" to from.toString()
                ),
                mapOf(
                    "metrics" to "End date",
                    "value" to to.toString()
                ),
                mapOf(
                    "metrics" to "Requests",
                    "value" to logAnalyser.getRequestsAmount(logs).toString()
                ),
                mapOf(
                    "metrics" to "Average response size",
                    "value" to logAnalyser.getAverageResponseSize(logs).toString()
                )
            )
        )
        tablePrinter.printTable(table, header = "Overall information")
    }

    fun printTheMostPopularResources(logs: Table, maxLinesInTable: Int = Int.MAX_VALUE) {
        val resources = logAnalyser.getTheMostPopularResources(logs, maxLinesInTable)
        tablePrinter.printTable(resources, header = "The most popular resources")
    }

    fun printTheMostPopularStatuses(logs: Table, maxLinesInTable: Int = Int.MAX_VALUE) {
        val statuses = logAnalyser.getTheMostPopularStatuses(logs, maxLinesInTable)
        tablePrinter.printTable(statuses, header = "The most popular statuses")
    }

    fun printTheMostHighLoadedDays(logs: Table, maxLinesInTable: Int = Int.MAX_VALUE) {
        val days = logAnalyser.getTheMostHighLoadedDays(logs, maxLinesInTable)
        tablePrinter.printTable(days, header = "The most highloaded days")
    }

    fun printTheMostActiveUsers(logs: Table, maxLinesInTable: Int = Int.MAX_VALUE) {
        val users = logAnalyser.getTheMostActiveUsers(logs, maxLinesInTable)
        tablePrinter.printTable(users, header = "The most active users")
    }
}
