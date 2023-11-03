package edu.project3

import java.io.File
import java.net.URL
import java.time.LocalDate


var sources: List<String> = mutableListOf()
var from: LocalDate? = null
var to: LocalDate? = null
var format: String? = null

val logAnalyser = LogAnalyser()
val logParser = LogParser()

fun main(params: Array<String>) {
    parseParams(params)

    val logs = logParser.parseAllLogs(getNonParsedSources())


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

