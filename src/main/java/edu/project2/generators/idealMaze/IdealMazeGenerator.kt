package edu.project2.generators.idealMaze

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import java.util.Stack

class IdealMazeGenerator {

    private lateinit var maze: Maze
    private lateinit var visited: MutableSet<Cell>

    public fun getMaze(height: Int, width: Int): Maze {
        maze = Maze(height, width)
        visited = mutableSetOf()

        val start = Cell(0, 0)
        maze.setCellType(start,CellType.PASSAGE)

        val trace = Stack<Cell>()

        var currentCell = start

        trace.add(start)
        while (trace.isNotEmpty()) {

            visited.add(currentCell)


            val nextCells = maze
                .getAdjacentCells(currentCell, 2)
                .filter { it !in visited }

            if(nextCells.isNotEmpty()) {

                val nextCell = nextCells.random()
                maze.setCellType(nextCell,CellType.PASSAGE)

                trace.add(nextCell)

                val pos = getRelativePosition(currentCell,nextCell)


                when(pos){
                    Position.UP ->
                        maze.setCellType(Cell(currentCell.row-1,currentCell.column),CellType.PASSAGE)
                    Position.RIGHT ->
                        maze.setCellType(Cell(currentCell.row,currentCell.column+1),CellType.PASSAGE)
                    Position.DOWN ->
                        maze.setCellType(Cell(currentCell.row+1,currentCell.column),CellType.PASSAGE)
                    Position.LEFT ->
                        maze.setCellType(Cell(currentCell.row,currentCell.column-1),CellType.PASSAGE)
                    Position.UNKNOWN -> continue
                }

                currentCell = nextCell
            }
            else
                currentCell = trace.pop()

        }

        return maze
    }

    private fun getRelativePosition(originalCell:Cell, cell:Cell):Position{

        if(originalCell == cell)
            return Position.UNKNOWN

        if(originalCell.row == cell.row){
            if(originalCell.column > cell.column)
                return Position.LEFT
            return Position.RIGHT
        }

        if(originalCell.column == cell.column){
            if(originalCell.row > cell.row)
                return Position.UP
            return Position.DOWN
        }

        return Position.UNKNOWN
    }

}
