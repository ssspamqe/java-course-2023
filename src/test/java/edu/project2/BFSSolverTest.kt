package edu.project2

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSSolver
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class BFSSolverTest : StringSpec({
    val solver = BFSSolver()


    lateinit var maze: Maze
    val height = 20
    val width = 20


    beforeEach {
        maze = IdealMazeGenerator().getMaze(height, width)
    }

    "Solver should throw an exception if not passing CellType.WALL as start cell" {
        checkAll(Arb.int(-height, 2 * height), Arb.int(-width, 2 * width)) { row, column ->

            val start = Cell(row, column)

            checkAll(Arb.int(-height, 2 * height), Arb.int(-width, 2 * width)) { row, column ->

                val end = Cell(row, column)

                // вот тут нам нужно узнать, является ли ячейка свободной или нет, и исходя из этого определить тест
                if (maze.getCellType(start) != CellType.PASSAGE || maze.getCellType(end) != CellType.PASSAGE) {
                    shouldThrowAny {
                        solver.solve(maze, start, end)
                    }
                } else
                    shouldNotThrowAny {
                        solver.solve(maze, start, end)
                    }

            }
        }
    }
})
