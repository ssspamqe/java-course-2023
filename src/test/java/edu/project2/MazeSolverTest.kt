package edu.project2

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSSolver
import edu.project2.solvers.DFSSolver
import edu.project2.solvers.asyncDFSSolver.AsyncDFSSolver
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MazeSolverTest : ShouldSpec({

    val SIDE = 15;

    var solvers = listOf(DFSSolver(), BFSSolver(), AsyncDFSSolver())
    val mazeGenerator = IdealMazeGenerator()

    fun getStartCoordinates(maze: Maze): CellCoordinates? {
        for (row in 0..<maze.height) {
            for (col in 0..<maze.width) {
                val cell = CellCoordinates(row, col)

                val cellType = maze.getCellType(cell)
                if (cellType == CellType.PASSAGE)
                    return cell
            }
        }
        return null
    }

    fun getEndCoordinates(maze: Maze): CellCoordinates? {
        for (row in maze.height - 1 downTo 0) {
            for (col in maze.width - 1 downTo 0) {
                val cell = CellCoordinates(row, col)

                val cellType = maze.getCellType(cell)
                if (cellType == CellType.PASSAGE)
                    return cell
            }
        }
        return null
    }

    fun getNextCell(maze: Maze, cell: CellCoordinates, prevCell: CellCoordinates): CellCoordinates {
        val cellUp = CellCoordinates(cell.row - 1, cell.column)
        if (maze.getCellType(cellUp).solution && cellUp != prevCell) {
            return cellUp

        }

        val cellDown = CellCoordinates(cell.row + 1, cell.column)
        if (maze.getCellType(cellDown).solution && cellDown != prevCell) {
            return cellDown

        }

        val cellRight = CellCoordinates(cell.row, cell.column + 1)
        if (maze.getCellType(cellRight).solution && cellRight != prevCell) {
            return cellRight

        }

        val cellLeft = CellCoordinates(cell.row, cell.column - 1)
        if (maze.getCellType(cellLeft).solution && cellLeft != prevCell) {
            return cellLeft
        }
        return CellCoordinates(-1, -1)
    }

    context("Maze solver") {
        val maze = mazeGenerator.getMaze(SIDE)
        solvers.forEach { solver ->
            should("solve the maze with ${solver.javaClass.name} implementation") {
                val start = getStartCoordinates(maze)
                start shouldNotBe null

                val end = getEndCoordinates(maze)
                end shouldNotBe null

                val solvedMaze = solver.solve(maze, start!!, end!!)

                var currentCell = start!!
                var prevCell = CellCoordinates(-1, -1)
                var steps = 0
                while (steps <= SIDE * SIDE && currentCell != end) {
                    val nextCell = getNextCell(solvedMaze, currentCell, prevCell)
                    prevCell = currentCell
                    currentCell = nextCell
                    steps++
                }
                currentCell shouldBe end
            }
        }

    }
})

