package edu.project2.Maze

import edu.project2.Cell
import org.apache.logging.log4j.LogManager
import java.lang.StringBuilder

class Maze(height: Int, width: Int) {

    private val LOGGER = LogManager.getLogger()

    private val maze:MutableList<MutableList<CellType>>

    val height:Int
    val width:Int

    init {

        this.height = height
        this.width = width

        maze = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()

    }

    public constructor(size:Int):this(size,size)


    public fun setCell(cell: Cell, newType:CellType){
        if(cell.row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height)")
        if(cell.column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width)")

        maze[cell.row][cell.column] = newType
    }

    public fun printMaze(){

        LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width+2))

        maze.forEach { line ->
            val symbolLine = StringBuilder()
            line.forEach {
                symbolLine.append(
                    if(it == CellType.WALL)
                        CellType.WALL.getSymbol()
                    else
                        CellType.PASSAGE.getSymbol()
                )
            }

            LOGGER.info(CellType.WALL.getSymbol().toString() + symbolLine + CellType.WALL.getSymbol())

        }

        LOGGER.info(CellType.WALL.getSymbol().toString().repeat(width+2))
    }

    public fun getCell(row:Int, column:Int):CellType{
        if(row !in 0 until height || column !in 0 until width)
            return CellType.OUT_OF_BOUNDS
        return maze[row][column]
    }


}
