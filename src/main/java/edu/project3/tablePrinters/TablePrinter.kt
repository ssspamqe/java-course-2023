package edu.project3.tablePrinters

import edu.project3.Table

abstract class TablePrinter {
    abstract fun printTable(
        table: Table,
        amount: Int = Int.MAX_VALUE,
        header: String = ""
    )

    protected fun String.center(size: Int): String =
        this.padStart(this.length + (size - this.length) / 2).padEnd(size)
}
