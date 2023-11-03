package edu.project3.tablePrinters

abstract class TablePrinter {
    abstract fun printListOfMaps(table:List<Map<String,String>>)

    protected fun getColumnNames(table:List<Map<String,String>>):List<String>{
        if(table.isEmpty())
            throw IllegalArgumentException("Given and empty table")

        return table[0].keys.toList()
    }

    protected fun getColumnLengths(table:List<Map<String,String>>):List<Int> =
        getColumnNames(table).map { getMaxLenOfItemInColumn(table,it) }

    protected fun getMaxLenOfItemInColumn(table: List<Map<String, String>>, column: String): Int =
        table.maxOf{ it[column]!!.length }
}
