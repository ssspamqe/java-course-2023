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

var tablePrinter:TablePrinter = MarkdownTablePrinter()

fun main(params: Array<String>) {
    parseParams(params)

    var logs = logParser.parseAllLogs(getNonParsedSources())
    logs = logAnalyser.getDateConstrainedLogs(logs,from,to)

    printOverallInfo(logs)


}

private fun parseParams(params: Array<String>) {

    var i = 0
    while (i != params.size) {

        when (params[i]) {
            "--path" -> {
                i++
                while (i != params.size && params[i] !in listOf("--from", "--format")) {
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
                if(params[i+1] == "adoc")
                    tablePrinter = ADocTablePrinter()
                i++
            }

            else -> i++
        }
    }

    sources = sources.toList()
}

private fun getNonParsedSources():List<String>{
    val nonParsedSources = mutableListOf<String>()

    sources.forEach { src ->

        val urlRegex = "(?:http)s?:\\/\\/.*".toRegex()

        if(src.matches(urlRegex))
            nonParsedSources.addAll(URL(src).readText().split("\n"))
        else
            File(src).useLines {
                nonParsedSources.addAll(it.toList())
            }
    }

    return nonParsedSources.toList()
}

private fun printOverallInfo(logs:Table){

    val table = Table(listOf(
        mapOf("metrics" to "Files", "value" to sources.toString()),
        mapOf("metrics" to "Start date", "value" to from.toString()),
        mapOf("metrics" to "End date", "value" to to.toString()),
        mapOf("metrics" to "Requests","value" to logAnalyser.getRequestsAmount(logs).toString()),
        mapOf("metrics" to "Average response size", "value" to logAnalyser.getAverageResponseSize(logs).toString())
    ))

    tablePrinter.printTable(table, heading = "Overall information")
}
