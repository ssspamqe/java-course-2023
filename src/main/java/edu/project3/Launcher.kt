package edu.project3

import java.io.File
import java.time.LocalDate


var sources: List<String> = mutableListOf()
var from: LocalDate? = null
var to: LocalDate? = null
var format: String? = null

fun main(params: Array<String>) {
    parseParams(params)

    val src = sources[0]


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

