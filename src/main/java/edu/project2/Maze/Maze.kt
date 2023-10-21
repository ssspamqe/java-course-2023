package edu.project2.Maze

import org.apache.logging.log4j.LogManager

class Maze : Cloneable {

    private val LOGGER = LogManager.getLogger()

    private var matrix: List<MutableList<CellType>>

    val height: Int
    val width: Int

    constructor(height: Int, width: Int) {

        this.height = height
        this.width = width

        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()
    }

    constructor(){
        this.height = 0
        this.width = 0

        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()
    }


    public constructor(size: Int) : this(size, size)

    constructor(oldMatrix: List<MutableList<CellType>>) {

        this.height = oldMatrix.size
        this.width = oldMatrix[0].size

        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()

        oldMatrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cellType ->
                matrix[rowIndex][columnIndex] = cellType
            }
        }
    }


    public fun setCellType(cell: Cell, newType: CellType): Maze {

        if (cell.row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height), given row is ${cell.row}")
        if (cell.column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width), given column is ${cell.column}")


        matrix[cell.row][cell.column] = newType
        return this
    }

    public fun printMaze(printBounds:Boolean = false) {

        if(printBounds)
            LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width + 2))

        matrix.forEach { line ->
            val symbolLine = StringBuilder()
            line.forEach {
                symbolLine.append(it.getSymbol())
            }

            if(printBounds)
                LOGGER.info(CellType.WALL.getSymbol().toString() + symbolLine + CellType.WALL.getSymbol())
            else
                LOGGER.info(symbolLine)
        }

        if(printBounds)
            LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width + 2))
    }

    public fun getCellType(cell: Cell): CellType {
        if (cell.row !in 0 until height || cell.column !in 0 until width)
            return CellType.OUT_OF_BOUNDS
        return matrix[cell.row][cell.column]
    }

    public override fun clone(): Maze {
        return Maze(matrix)
    }

    fun getAdjacentCells(cell: Cell): List<Cell> {
        return getAdjacentCells(cell, 1)
    }

    fun getAdjacentCells(cell:Cell, distance:Int):List<Cell>{
        if(distance<=0)
            throw IllegalArgumentException("Distance must be positive number ")

        val adjacentCells = mutableListOf<Cell>()

        if (cell.row >= distance) //up
            adjacentCells.add(Cell(cell.row - distance, cell.column))

        if (cell.column < width - distance) //right
            adjacentCells.add(Cell(cell.row, cell.column + distance))

        if (cell.row < height - distance) //down
            adjacentCells.add(Cell(cell.row + distance, cell.column))

        if (cell.column >= distance)
            adjacentCells.add(Cell(cell.row, cell.column - distance))

        return adjacentCells
    }


}
