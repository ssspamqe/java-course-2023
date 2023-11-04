package edu.project3.tablePrinters

import edu.project3.Table
import org.apache.logging.log4j.LogManager
import kotlin.math.min

class ADocTablePrinter : TablePrinter() {

    private val LOGGER = LogManager.getLogger()

    override fun printTable(table: Table, amount: Int, heading:String) {

        if(heading!="")
            LOGGER.info("= $heading")

        LOGGER.info("|===")

        printColumnNames(table)
        printTableItems(table,amount)

        LOGGER.info("|===")
    }

    private fun printColumnNames(table: Table) {

        val columns = table.columns
        val columnLengths = table.getColumnsLengths()

        LOGGER.info(buildString {
            columns.forEach {
                append("|"+it.center(columnLengths[it]!!))
            }
        })

        LOGGER.info("")
    }

    private fun printTableItems(table: Table, amount: Int) {
        val columns = table.columns
        val columnLengths = table.getColumnsLengths()

        for (line in 0 until min(amount, table.getSize())) {
            LOGGER.info(buildString {
                columns.forEach{column ->
                    append("|" + table.getCell(line,column).center(columnLengths[column]!!))
                }
            })
        }
    }
}
