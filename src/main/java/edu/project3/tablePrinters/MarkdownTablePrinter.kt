package edu.project3.tablePrinters

import org.apache.logging.log4j.LogManager
import kotlin.math.min

class MarkdownTablePrinter : TablePrinter() {

    private val LOGGER = LogManager.getLogger()

    public override fun printListOfMaps(table: List<Map<String, String>>, amount: Int) {
        printColumnNames(table)
        printHeadSeparator(table)
        printTableItems(table, amount)
    }

    private fun printColumnNames(table: List<Map<String, String>>) {
        val columnNames = getColumnNames(table)
        val columnLengths = getColumnLengths(table)

        LOGGER.info(buildString {
            append("|")
            for (i in columnNames.indices)
                append(columnNames[i].center(columnLengths[i])+ "|")
        })
    }

    private fun printHeadSeparator(table: List<Map<String, String>>) {
        val columnLengths = getColumnLengths(table)


        LOGGER.info(buildString {
            append("|")
            columnLengths.forEach {
                append(":"+"-".repeat(it - 2) + ":" + "|")
            }
        })
    }

    private fun printTableItems(table: List<Map<String, String>>, amount: Int = Int.MAX_VALUE) {
        val columnLengths = getColumnLengths(table)
        val columnNames = getColumnNames(table)

        for (line in 0 until min(amount, table.size)) {

            LOGGER.info(buildString {
                append("|")
                for (i in columnNames.indices) {
                    append(table[line][columnNames[i]]!!.center(columnLengths[i]) + "|")
                }
            })
        }
    }
}




