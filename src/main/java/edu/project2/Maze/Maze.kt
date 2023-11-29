package edu.project2.Maze

import org.apache.logging.log4j.LogManager

data class Maze(
    val height: Int,
    val width: Int
) : Cloneable {

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
                setCellType(CellCoordinates(rowIndex, columnIndex), cellType)
            }
        }
    }

    public fun setCellType(cellCoordinates: CellCoordinates, newType: CellType): Maze {

        if (cellCoordinates.row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height), given row is ${cellCoordinates.row}")
        if (cellCoordinates.column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width), given column is ${cellCoordinates.column}")

        (matrix!![cellCoordinates.row] as MutableList<CellType>)[cellCoordinates.column] = newType
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
                LOGGER.info("${CellType.WALL.getSymbol()}$symbolLine${CellType.WALL.getSymbol()}")
            else
                LOGGER.info(symbolLine)
        }

        if (printBounds)
            LOGGER.info(CellType.WALL.getSymbol().repeat(width + 2))
    }

    public fun getCellType(cellCoordinates: CellCoordinates): CellType =
        if (cellCoordinates.row !in 0 until height || cellCoordinates.column !in 0 until width)
            CellType.OUT_OF_BOUNDS
        else matrix!![cellCoordinates.row][cellCoordinates.column]

    fun getAdjacentCells(cellCoordinates: CellCoordinates): List<CellCoordinates> = getAdjacentCells(cellCoordinates, 1)


    fun getAdjacentCells(cellCoordinates: CellCoordinates, distance: Int): List<CellCoordinates> {

        if (distance <= 0)
            throw IllegalArgumentException("Distance must be positive number ")

        val adjacentCellCoordinates = mutableListOf<CellCoordinates>()

        if (cellCoordinates.row >= distance) //up
            adjacentCellCoordinates.add(CellCoordinates(cellCoordinates.row - distance, cellCoordinates.column))

        if (cellCoordinates.column < width - distance) //right
            adjacentCellCoordinates.add(CellCoordinates(cellCoordinates.row, cellCoordinates.column + distance))

        if (cellCoordinates.row < height - distance) //down
            adjacentCellCoordinates.add(CellCoordinates(cellCoordinates.row + distance, cellCoordinates.column))

        if (cellCoordinates.column >= distance)
            adjacentCellCoordinates.add(CellCoordinates(cellCoordinates.row, cellCoordinates.column - distance))

        return adjacentCellCoordinates
    }

    public override fun clone(): Maze = Maze(matrix!!)
}
