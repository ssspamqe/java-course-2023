package edu.project3.tablePrinters

import kotlin.math.max

abstract class TablePrinter {
    abstract fun printListOfMaps(table:List<Map<String,String>>, amount:Int = Int.MAX_VALUE)

    protected fun getColumnNames(table:List<Map<String,String>>):List<String>{
        if(table.isEmpty())
            throw IllegalArgumentException("Given and empty table")

        return table[0].keys.toList()
    }

    protected fun getColumnLengths(table:List<Map<String,String>>):List<Int> =
        getColumnNames(table).map { getMaxLenOfItemInColumn(table,it) }

    protected fun getMaxLenOfItemInColumn(table: List<Map<String, String>>, column: String): Int =
        max(table.maxOf{ it[column]!!.length }, column.length)
}
