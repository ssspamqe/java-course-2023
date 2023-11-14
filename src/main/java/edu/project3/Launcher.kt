package edu.project3

import edu.project3.logWorkers.LogAnalyser
import edu.project3.logWorkers.LogParser
import edu.project3.statsPrinter.StatsPrinter
import edu.project3.tablePrinters.ADocTablePrinter
import edu.project3.tablePrinters.MarkdownTablePrinter
import edu.project3.tablePrinters.TablePrinter
import org.apache.logging.log4j.LogManager
import java.time.LocalDate


var sources: List<String> = mutableListOf()
var from: LocalDate? = null
var to: LocalDate? = null

val logAnalyser = LogAnalyser()
val logParser = LogParser()

val LOGGER = LogManager.getLogger()

var tablePrinter: TablePrinter = MarkdownTablePrinter()

var maxLinesInTable = 5

lateinit var statsPrinter: StatsPrinter

fun main(params: Array<String>) {
    parseParams(params)

    val nonParsedLogs = logParser.combineLogs(sources)
    if (nonParsedLogs.isEmpty()) {
        LOGGER.info("No logs passed to program")
        return
    }
    var logs = logParser.parseAllLogs(nonParsedLogs)
    logs = logAnalyser.getDateConstrainedLogs(logs, from, to)


    statsPrinter = StatsPrinter(tablePrinter)
    statsPrinter.printOverallInfo(logs, sources, from, to)
    LOGGER.info("")

    statsPrinter.printTheMostPopularStatuses(logs, maxLinesInTable)
    LOGGER.info("")
    statsPrinter.printTheMostHighLoadedDays(logs, maxLinesInTable)
    LOGGER.info("")
    statsPrinter.printTheMostActiveUsers(logs, maxLinesInTable)
    LOGGER.info("")
    statsPrinter.printTheMostPopularStatuses(logs, maxLinesInTable)
}

private fun parseParams(params: Array<String>) {
    var i = 0
    while (i != params.size) {
        when (params[i]) {
            "--sources" -> {
                while (i + 1 != params.size && params[i + 1] !in listOf("--from", "--format", "--format", "--lines")) {
                    i++
                    (sources as MutableList<String>).add(params[i])
                }
            }

            "--from" -> {
                from = LocalDate.parse(params[i + 1])
            }

            "--to" -> {
                to = LocalDate.parse(params[i + 1])
            }

            "--format" -> {
                if (params[i + 1] == "adoc")
                    tablePrinter = ADocTablePrinter()
            }

            "--lines" -> {
                maxLinesInTable = params[i + 1].toInt()
            }
        }
        i++
    }

    sources = sources.toList()
}
