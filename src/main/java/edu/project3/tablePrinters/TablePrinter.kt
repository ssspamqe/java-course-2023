package edu.project3.tablePrinters

import edu.project3.Table

abstract class TablePrinter {
    abstract fun printTable(
        table: Table,
        amount: Int = Int.MAX_VALUE,
        heading: String = ""
    )

    //    protected fun getColumnNames(table: List<Map<String, String>>): List<String> {
//        if (table.isEmpty())
//            throw IllegalArgumentException("Given and empty table")
//
//        return table[0].keys.toList()
//    }
    protected fun String.center(size: Int): String =
        this.padStart(this.length + (size - this.length) / 2).padEnd(size)


}
