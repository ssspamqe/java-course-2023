package edu.project3

import jakarta.validation.constraints.NotEmpty
import kotlin.math.max

data class Table(
    @NotEmpty
    private var rows: List<Map<String, String?>>
) {
    val columns: List<String>

    init {
        val keys = rows[0].keys as MutableSet<String>
        rows.forEach {
            keys.addAll(it.keys)
        }
        columns = keys.toList()
    }

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

    fun getColumnsLengths(): Map<String, Int> {
        val a = "a"
        a.length
        return columns.associateWith { getMaxLengthOfItemInColumn(it) }
    }

    fun getMaxLengthOfItemInColumn(column: String): Int {
        if (column !in columns)
            throw IllegalArgumentException("No such column")
        return max(rows.maxOf {
            if(it[column] == null)
                4
            else
                it[column]!!.length
        }, column.length)
    }

    fun getRow(line: Int) = rows[line]

    fun getCell(line: Int, column: String) = rows[line][column]

    fun getSize() = rows.size

    fun getRows() = rows
}

