package edu.project2.Maze

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell
import java.lang.StringBuilder

class Maze(
    private var height: Int,
    private var width: Int
) {

    private val LOGGER = LogManager.getLogger()

    private val maze:MutableList<MutableList<CellType>>


    init {

        this.height = height
        this.width = width

        maze = List(height) { _ ->
            List(width) { _ -> CellType.WALL }.toMutableList()
        }.toMutableList()

    }

    public constructor(size:Int):this(size,size)


    public fun setCell(row:Int, column:Int, newType:CellType){
        if(row !in 0 until height)
            throw IllegalArgumentException("row must be in [0;height)")
        if(column !in 0 until width)
            throw IllegalArgumentException("column must be in [0;width)")

        maze[row][column] = newType
    }

    public fun printMaze(){
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

            LOGGER.info(symbolLine)

        }
    }



}
