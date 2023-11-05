package edu.project3.tablePrinters

import edu.project3.Table
import org.apache.logging.log4j.LogManager
import kotlin.math.min

class MarkdownTablePrinter : TablePrinter() {

    private val LOGGER = LogManager.getLogger()

    public override fun printTable(table: Table, amount: Int, header: String) {

        if (header != "")
            LOGGER.info("#### $header")

        printColumnNames(table)
        printHeadSeparator(table)
        printTableItems(table, amount)
    }

    private fun printColumnNames(table: Table) {
        val columns = table.columns
        val columnLengths = table.getAllColumnLengths()

        LOGGER.info(buildString {
            append("|")

            columns.forEach { column ->
                append(column.center(columnLengths[column]!!) + "|")
            }
        })
    }

    private fun printHeadSeparator(table: Table) {
        val columnLengths = table.getAllColumnLengths()


        LOGGER.info(buildString {
            append("|")
            columnLengths.forEach {
                append(":" + "-".repeat(it.value - 2) + ":" + "|")
            }
        })
    }

    private fun printTableItems(table: Table, amount: Int = Int.MAX_VALUE) {
        val columnLengths = table.getAllColumnLengths()
        val columns = table.columns

        for (line in 0 until min(amount, table.getSize())) {
            LOGGER.info(buildString {
                append("|")
                columns.forEach { column ->
                    if(table.getCell(line,column) == null)
                        append("null".center(columnLengths[column]!!) + "|")
                    else
                        append(table.getCell(line, column)!!.center(columnLengths[column]!!) + "|")
                }
            })
        }
    }

}




