package edu.project3

import kotlin.math.max

data class Table(private var rows: List<Map<String, String>>) {
    private var columns: List<String> = rows[0].keys.toList()
        get() = field

    fun addAll(newRows: List<Map<String, String>>) {
        val mutableRows = rows.toMutableList()
        mutableRows.addAll(newRows)
        rows = mutableRows.toList()
    }

    fun add(newRow: Map<String, String>) {
        val mutableRows = rows.toMutableList()
        mutableRows.add(newRow)
        rows = mutableRows.toList()
    }

    fun getColumnsLengths():Map<String,Int> {
        return columns.associateWith { getMaxLengthOfItemInColumn(it) }
    }

    fun getMaxLengthOfItemInColumn(column:String): Int{
        if(column !in columns)
            throw IllegalArgumentException("No such column")
        return max(rows.maxOf { it[column]!!.length },column.length)
    }
}

