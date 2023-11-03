package edu.project3

import edu.project3.logWorkers.LogAnalyser
import edu.project3.logWorkers.LogParser
import edu.project3.tablePrinters.ADocTablePrinter
import edu.project3.tablePrinters.MarkdownTablePrinter
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.time.LocalDate


var sources: List<String> = mutableListOf()
var from: LocalDate? = null
var to: LocalDate? = null
var format: String? = null

val logAnalyser = LogAnalyser()
val logParser = LogParser()

val LOGGER = LogManager.getLogger()

fun main(params: Array<String>) {
    parseParams(params)

    var logs = logParser.parseAllLogs(getNonParsedSources())
    logs = logAnalyser.getDateConstrainedLogs(logs,from,to)

    MarkdownTablePrinter().printListOfMaps(logs)

    LOGGER.info("\n")

    printOverallData(logs)


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
                format = params[i + 1]
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

private fun printOverallData(logs:List<Map<String,String>>){

    val table = mutableListOf<Map<String,String>>()

    table.addAll(listOf(
        mapOf("metrics" to "Files", "value" to sources.toString()),
        mapOf("metrics" to "Start date", "value" to from.toString()),
        mapOf("metrics" to "End date", "value" to to.toString()),
        mapOf("metrics" to "Requests","value" to logAnalyser.getRequestsAmount(logs).toString()),
        mapOf("metrics" to "Average response size", "value" to logAnalyser.getAverageResponseSize(logs).toString())
    ))

    printTable(table)
}

private fun printTable(table:List<Map<String,String>>){
    MarkdownTablePrinter().printListOfMaps(table)
}

