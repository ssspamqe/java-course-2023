package edu.project2.Maze

import org.apache.logging.log4j.LogManager

data class Maze(
    val height: Int,
    val width: Int
) :Cloneable {

    private val LOGGER = LogManager.getLogger()

    private var matrix: List<List<CellType>>? = null //такова цена дата класса....
    // (тут все равно надо копировать это поле, а если вызвать .copy(), то скопируются только height:Int и width:Int
    // и в init{} матрица снова будет состоять только из стен) Зато повторяющегося кода меньше стало

    init {
        if (matrix == null)
            matrix = List(height) { List(width) { CellType.WALL } }
    }

    constructor(oldMatrix: List<List<CellType>>) : this(oldMatrix.size, oldMatrix[0].size) {
        val height = oldMatrix.size
        val width = oldMatrix[0].size

        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()

        oldMatrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cellType ->
                setCellType(Cell(rowIndex, columnIndex), cellType)
            }
        }
    }

    public fun setCellType(cell: Cell, newType: CellType): Maze {

        if (cell.row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height), given row is ${cell.row}")
        if (cell.column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width), given column is ${cell.column}")

        (matrix!![cell.row] as MutableList<CellType>)[cell.column] = newType
        return this
    }

    public fun printMaze(printBounds: Boolean = false) {

        if (printBounds)
            LOGGER.info(CellType.WALL.getSymbol().repeat(width + 2))

        matrix!!.forEach { line ->

            val symbolLine = buildString {
                line.forEach {
                    append(it.getSymbol())
                }
            }

            if (printBounds)
                LOGGER.info(CellType.WALL.getSymbol() + symbolLine + CellType.WALL.getSymbol())
            else
                LOGGER.info(symbolLine)
        }

        if (printBounds)
            LOGGER.info(CellType.WALL.getSymbol().repeat(width + 2))
    }

    public fun getCellType(cell: Cell): CellType =
        if (cell.row !in 0 until height || cell.column !in 0 until width)
            CellType.OUT_OF_BOUNDS
        else matrix!![cell.row][cell.column]

    fun getAdjacentCells(cell: Cell): List<Cell> = getAdjacentCells(cell, 1)


    fun getAdjacentCells(cell: Cell, distance: Int): List<Cell> {

        if (distance <= 0)
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

    public override fun clone(): Maze = Maze(matrix!!)
}
