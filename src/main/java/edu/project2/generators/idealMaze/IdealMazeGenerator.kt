package edu.project2.generators.idealMaze

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.MazeGenerator
import java.util.*

class IdealMazeGenerator : MazeGenerator {

    private lateinit var maze: Maze
    private lateinit var visited: MutableSet<Cell>

    public fun getMaze(height: Int, width: Int): Maze {

        if (height <= 0 || width <= 0)
            throw IllegalArgumentException("Sizes of maze must be positive integers")

        maze = Maze(height, width)
        visited = mutableSetOf()

        val start = Cell(0, 0)
        maze.setCellType(start, CellType.PASSAGE)

        procedureBacktracking(start)

        return maze
    }

    public fun getMaze(side: Int): Maze {
        return getMaze(side, side)
    }

    private fun procedureBacktracking(start: Cell) {

        val trace = Stack<Cell>()
        trace.add(start)
        var currentCell = start
        while (trace.isNotEmpty()) {

            visited.add(currentCell)

            val nextCells = maze
                .getAdjacentCells(currentCell, 2)
                .filter { it !in visited }

            if (nextCells.isNotEmpty()) {

                val nextCell = nextCells.random()
                maze.setCellType(nextCell, CellType.PASSAGE)

                trace.add(nextCell)

                when (getCellShift(currentCell, nextCell)) {
                    Shift.UP ->
                        maze.setCellType(Cell(currentCell.row - 1, currentCell.column), CellType.PASSAGE)

                    Shift.RIGHT ->
                        maze.setCellType(Cell(currentCell.row, currentCell.column + 1), CellType.PASSAGE)

                    Shift.DOWN ->
                        maze.setCellType(Cell(currentCell.row + 1, currentCell.column), CellType.PASSAGE)

                    Shift.LEFT ->
                        maze.setCellType(Cell(currentCell.row, currentCell.column - 1), CellType.PASSAGE)

                    Shift.UNKNOWN -> continue
                }
                currentCell = nextCell
            } else
                currentCell = trace.pop()
        }
    }

    private fun getCellShift(originalCell: Cell, cell: Cell): Shift {
        if (originalCell == cell)
            return Shift.UNKNOWN

        if (originalCell.row == cell.row) {
            if (originalCell.column > cell.column)
                return Shift.LEFT
            return Shift.RIGHT
        }

        if (originalCell.column == cell.column) {
            if (originalCell.row > cell.row)
                return Shift.UP
            return Shift.DOWN
        }

        return Shift.UNKNOWN
    }
}
