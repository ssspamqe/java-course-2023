package edu.project2

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.idealMaze.IdealMazeGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll

class MazeTest : StringSpec({

    val generator = IdealMazeGenerator()
    val height = 20
    val width = 20
    lateinit var maze: Maze

    beforeEach {
        maze = generator.getMaze(height, width)
    }


    "Method getCellType() should return CellType.PASSAGE or CellType.WALL if passing correct cell" {

        forAll(Arb.int(0 until height), Arb.int(0 until width)) { row, column ->

            val cell = Cell(row, column)

            val type = maze.getCellType(cell)

            type in listOf(CellType.PASSAGE, CellType.WALL)
        }

    }

    "Method getCellType() should return CellType.OUT_OF_BOUNDS if passing incorrect cell" {

        forAll(Arb.int(-height until 0), Arb.int(width until 2 * width)) { row, column ->

            val cell = Cell(row, column)

            val type = maze.getCellType(cell)

            type == CellType.OUT_OF_BOUNDS
        }

    }


    "Method setCellType() should change CellType of cell" {

        forAll(Arb.int(0 until height), Arb.int(0 until width)) { row, column ->

            val cell = Cell(row, column)

            maze.setCellType(cell, CellType.OUT_OF_BOUNDS)

            maze.getCellType(cell) == CellType.OUT_OF_BOUNDS
        }

    }
})
