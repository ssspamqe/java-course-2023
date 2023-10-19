package edu.project2.generators

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import java.util.*
import kotlin.random.Random

class ChaoticMazeGenerator() {

    private var height: Int = 0
    private var width: Int = 0

    private var maze: Maze? = null

    public fun getMaze(height: Int, width: Int, wallChance: Int): Maze {
        this.height = height
        this.width = width
        return generateMaze(height, width, wallChance)
    }

    private fun generateMaze(height: Int, width: Int, wallChance: Int): Maze {

        maze = Maze(height, width)


        val startCell = Cell(Random.nextInt(height), Random.nextInt(width))


        val trace: Stack<Cell> = Stack()
        trace.add(startCell)

        val visited = hashSetOf(startCell)


        while (trace.isNotEmpty()) {

            val currentCell = trace.pop()
            val nextCells = getAdjacentCells(currentCell).filter { it !in visited }
            val nextCellsAssignment = getNextCellsAssignment(nextCells.size, wallChance)

            if (nextCellsAssignment.size != nextCells.size)
                print(1)

            //set up next cells types and update trace
            nextCellsAssignment.forEachIndexed { index, cellType ->
                val cell = nextCells[index]
                maze!!.setCellType(cell, cellType)


                visited.add(cell)
                if (cellType == CellType.PASSAGE)
                    trace.add(cell)
            }

        }

        return maze as Maze
    }

    private fun getNextCellsAssignment(amount: Int, wallChance: Int): List<CellType> {

        if (amount == 0)
            return emptyList()

        if (wallChance !in 0..100)
            throw IllegalArgumentException("wallChance must be in range [0;100]")

        val assignment = mutableListOf(CellType.PASSAGE)

        while (assignment.size < amount) {
            val randInt = Random.nextInt(1, 101)

            if (randInt <= wallChance)
                assignment.add(CellType.WALL)
            else
                assignment.add(CellType.PASSAGE)
        }

        assignment.shuffle()
        return assignment
    }


    private fun getAdjacentCells(cell: Cell): List<Cell> {
        val adjacentCells = mutableListOf<Cell>()


        if (cell.row > 0) //up
            adjacentCells.add(Cell(cell.row - 1, cell.column))

        if (cell.column < width - 1) //right
            adjacentCells.add(Cell(cell.row, cell.column + 1))

        if (cell.row < height - 1) //down
            adjacentCells.add(Cell(cell.row + 1, cell.column))

        if (cell.column > 0)
            adjacentCells.add(Cell(cell.row, cell.column - 1))


        return adjacentCells
    }


}
