package edu.project2.generators.idealMaze

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.MazeGenerator
import java.util.*

class IdealMazeGenerator : MazeGenerator {

    private lateinit var maze: Maze
    private lateinit var visited: HashSet<CellCoordinates>

    private var height = 0
    private var width = 0

    private val random = Random()
    public fun getMaze(height: Int, width: Int): Maze {

        this.height = height
        this.width = width

        if (height <= 0 || width <= 0)
            throw IllegalArgumentException("Sizes of maze must be positive integers")

        maze = Maze(height, width)
        visited = hashSetOf()

        val start = CellCoordinates(0, 0)
        maze.setCellType(start, CellType.PASSAGE)

        procedureBacktracking(start)


        if (width % 2 == 0)
            fillRightBorder()

        if (height % 2 == 0)
            fillDownBorder()

        return maze
    }

    public fun getMaze(side: Int): Maze = getMaze(side, side)


    private fun procedureBacktracking(start: CellCoordinates) {

        val trace = Stack<CellCoordinates>()
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

                changeTransitionCellTypeToPassage(currentCell, getCellShift(currentCell, nextCell))

                currentCell = nextCell
            } else
                currentCell = trace.pop()
        }
    }

    private fun changeTransitionCellTypeToPassage(currentCellCoordinates: CellCoordinates, shift: Shift) {
        when (shift) {
            Shift.UP ->
                maze.setCellType(
                    CellCoordinates(currentCellCoordinates.row - 1, currentCellCoordinates.column),
                    CellType.PASSAGE
                )

            Shift.RIGHT ->
                maze.setCellType(
                    CellCoordinates(currentCellCoordinates.row, currentCellCoordinates.column + 1),
                    CellType.PASSAGE
                )

            Shift.DOWN ->
                maze.setCellType(
                    CellCoordinates(currentCellCoordinates.row + 1, currentCellCoordinates.column),
                    CellType.PASSAGE
                )

            Shift.LEFT ->
                maze.setCellType(
                    CellCoordinates(currentCellCoordinates.row, currentCellCoordinates.column - 1),
                    CellType.PASSAGE
                )

            Shift.UNKNOWN -> return
        }
    }

    private fun getCellShift(originalCellCoordinates: CellCoordinates, cellCoordinates: CellCoordinates): Shift {
        if (originalCellCoordinates == cellCoordinates)
            return Shift.UNKNOWN

        if (originalCellCoordinates.row == cellCoordinates.row) {
            if (originalCellCoordinates.column > cellCoordinates.column)
                return Shift.LEFT
            return Shift.RIGHT
        }

        if (originalCellCoordinates.column == cellCoordinates.column) {
            if (originalCellCoordinates.row > cellCoordinates.row)
                return Shift.UP
            return Shift.DOWN
        }

        return Shift.UNKNOWN
    }

    private fun fillRightBorder() {
        val column = width - 1

        var prevFilled = false

        for (row in 0 until height) {
            if (maze.getCellType(CellCoordinates(row, column - 1)) == CellType.PASSAGE
                && random.nextBoolean()
                && !prevFilled
            ) {
                maze.setCellType(CellCoordinates(row, column), CellType.PASSAGE)
                prevFilled = true
            } else
                prevFilled = false
        }
    }

    private fun fillDownBorder() {
        val row = height - 1

        var prevFilled = false

        for (column in 0 until width) {
            if (maze.getCellType(CellCoordinates(row - 1, column)) == CellType.PASSAGE
                && random.nextBoolean()
                && !prevFilled
            ) {
                maze.setCellType(CellCoordinates(row, column), CellType.PASSAGE)
                prevFilled = true
            } else
                prevFilled = false
        }
    }
}
