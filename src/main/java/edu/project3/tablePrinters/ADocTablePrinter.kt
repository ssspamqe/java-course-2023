package edu.project3.tablePrinters

import edu.project3.Table
import org.apache.logging.log4j.LogManager
import kotlin.math.min

class ADocTablePrinter : TablePrinter() {

    private val LOGGER = LogManager.getLogger()

    override fun printTable(table: Table, amount: Int, header: String) {
        if (header != "")
            LOGGER.info("= $header")

        LOGGER.info("|===")

        printColumnNames(table)
        printTableItems(table, amount)

        LOGGER.info("|===")
    }

    private fun printColumnNames(table: Table) {
        val columns = table.columns
        val columnLengths = table.getAllColumnLengths()

        LOGGER.info(buildString {
            columns.forEach {
                val columnLen = columnLengths[it]!!
                append("|${it.center(columnLen)}")
            }
        })

        LOGGER.info("")
    }

    private fun printTableItems(table: Table, amount: Int) {
        val columns = table.columns
        val columnLengths = table.getAllColumnLengths()

        for (line in 0 until min(amount, table.size)) {
            LOGGER.info(buildString {
                columns.forEach { column ->
                    val columnLen = columnLengths[column]!!

                    if (table.getCell(line, column) == null)
                        append("|${"null".center(columnLen)}")
                    else
                        append("|${table.getCell(line, column)!!.center(columnLen)}")
                }
            })
        }
    }
}
