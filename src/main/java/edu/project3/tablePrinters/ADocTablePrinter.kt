package edu.project3.tablePrinters

import org.apache.logging.log4j.LogManager
import kotlin.math.min

class ADocTablePrinter : TablePrinter() {

    private val LOGGER = LogManager.getLogger()

    override fun printListOfMaps(table: List<Map<String, String>>, amount: Int) {
        LOGGER.info("|===")

        printColumnNames(table)
        printTableItems(table,amount)

        LOGGER.info("|===")
    }

    private fun printColumnNames(table: List<Map<String, String>>) {

        val columnNames = getColumnNames(table)
        val columnLengths = getColumnLengths(table)

        LOGGER.info(buildString {
            for (i in columnNames.indices) {
                append("|" + columnNames[i].center(columnLengths[i]))
            }
        })

        LOGGER.info("")
    }

    private fun printTableItems(table: List<Map<String, String>>, amount: Int) {
        val columnLengths = getColumnLengths(table)
        val columnNames = getColumnNames(table)

        for (line in 0 until min(amount, table.size)) {

            LOGGER.info(buildString {

                for (i in columnNames.indices) {
                    append("|" + table[line][columnNames[i]]!!.center(columnLengths[i]))
                }
            })
        }
    }
}
