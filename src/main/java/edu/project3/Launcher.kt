package edu.project3

import edu.project3.logWorkers.LogAnalyser
import edu.project3.logWorkers.LogParser
import edu.project3.tablePrinters.ADocTablePrinter
import edu.project3.tablePrinters.MarkdownTablePrinter
import edu.project3.tablePrinters.TablePrinter
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.time.LocalDate


var sources: List<String> = mutableListOf()
var from: LocalDate? = null
var to: LocalDate? = null

val logAnalyser = LogAnalyser()
val logParser = LogParser()

val LOGGER = LogManager.getLogger()

var tablePrinter: TablePrinter = MarkdownTablePrinter()

var maxLinesInTable = 5

fun main(params: Array<String>) {
    parseParams(params)

    val nonParsedLogs = getNonParsedLogs()
    if (nonParsedLogs.isEmpty()) {
        LOGGER.info("No logs passed to program")
        return
    }
    var logs = logParser.parseAllLogs(nonParsedLogs)
    logs = logAnalyser.getDateConstrainedLogs(logs, from, to)

    printOverallInfo(logs)
    LOGGER.info("")
    printTheMostPopularResources(logs)
    LOGGER.info("")
    printTheMostPopularStatuses(logs)
    LOGGER.info("")
    printTheMostHighLoadedDays(logs)
    LOGGER.info("")
    printTheMostActiveUsers(logs)

}

private fun parseParams(params: Array<String>) {

    var i = 0
    while (i != params.size) {

        when (params[i]) {
            "--sources" -> {
                i++
                while (i != params.size && params[i] !in listOf("--from", "--format","--format","--lines")) {
                    (sources as MutableList<String>).add(params[i])
                    i++;
                }
            }

            "--from" -> {
                from = LocalDate.parse(params[i + 1])
                i++
            }

            "--to" -> {
                to = LocalDate.parse(params[i + 1])
                i++
            }

            "--format" -> {
                if (params[i + 1] == "adoc")
                    tablePrinter = ADocTablePrinter()
                i++
            }

            "--lines" -> {
                maxLinesInTable = params[i + 1].toInt()
                i++
            }

            else -> i++
        }
    }

    sources = sources.toList()
}

private fun getNonParsedLogs(): List<String> {
    val nonParsedSources = mutableListOf<String>()

    sources.forEach { src ->

        val urlRegex = "(?:http)s?:\\/\\/.*".toRegex()

        if (src.matches(urlRegex))
            nonParsedSources.addAll(URL(src).readText().split("\n"))
        else
            File(src).useLines {
                nonParsedSources.addAll(it.toList())
            }
    }

    return nonParsedSources.toList()
}

private fun printOverallInfo(logs: Table) {

    val table = Table(
        listOf(
            mapOf("metrics" to "Files", "value" to sources.toString()),
            mapOf("metrics" to "Start date", "value" to from.toString()),
            mapOf("metrics" to "End date", "value" to to.toString()),
            mapOf("metrics" to "Requests", "value" to logAnalyser.getRequestsAmount(logs).toString()),
            mapOf("metrics" to "Average response size", "value" to logAnalyser.getAverageResponseSize(logs).toString())
        )
    )

    tablePrinter.printTable(table, header = "Overall information")
}

private fun printTheMostPopularResources(logs: Table) {
    val resources = logAnalyser.getTheMostPopularResources(logs, maxLinesInTable)
    tablePrinter.printTable(resources, header = "The most popular resources")
}

private fun printTheMostPopularStatuses(logs: Table) {
    val statuses = logAnalyser.getTheMostPopularStatuses(logs, maxLinesInTable)
    tablePrinter.printTable(statuses, header = "The most popular statuses")
}

private fun printTheMostHighLoadedDays(logs: Table) {
    val days = logAnalyser.getTheMostHighLoadedDays(logs, maxLinesInTable)
    tablePrinter.printTable(days, header = "The most highloaded days")
}

private fun printTheMostActiveUsers(logs: Table) {
    val users = logAnalyser.getTheMostActiveUsers(logs, maxLinesInTable)
    tablePrinter.printTable(users, header = "The most active users")
}
