package edu.project2.Maze

import edu.project2.Maze.Cell
import org.apache.logging.log4j.LogManager
import java.lang.StringBuilder

class Maze:Cloneable {

    private val LOGGER = LogManager.getLogger()

    private var matrix:List<MutableList<CellType>>

    val height:Int
    val width:Int

    constructor(height: Int, width: Int){
        this.height = height
        this.width = width

        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()
    }

    public constructor(size:Int):this(size,size)

    constructor(oldMatrix:List<MutableList<CellType>>){
        this.height = oldMatrix.size
        this.width = oldMatrix[0].size
        matrix = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()

        for (row in oldMatrix.indices)
            for (column in oldMatrix[row].indices)
                matrix[row][column] = oldMatrix[row][column]
    }




    public fun setCellType(cell: Cell, newType:CellType){
        if(cell.row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height)")
        if(cell.column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width)")

        matrix[cell.row][cell.column] = newType
    }

    public fun printMaze(){

        LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width+2))

        matrix.forEach { line ->
            val symbolLine = StringBuilder()
            line.forEach {
                symbolLine.append(it.getSymbol())
            }

            LOGGER.info(CellType.WALL.getSymbol().toString() + symbolLine + CellType.WALL.getSymbol())

        }

        LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width+2))
    }

    public fun getCellType(cell: Cell):CellType{
        if(cell.row !in 0 until height || cell.column !in 0 until width)
            return CellType.OUT_OF_BOUNDS
        return matrix[cell.row][cell.column]
    }

    public override fun clone():Maze{
        return Maze(matrix)
    }

}
