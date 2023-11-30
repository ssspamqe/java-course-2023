package edu.project2.generators.chaoticMaze

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import edu.project2.generators.MazeGenerator
import java.util.*
import kotlin.random.Random

class ChaoticMazeGenerator() : MazeGenerator {

    private lateinit var maze: Maze

    public fun getMaze(height: Int, width: Int, wallChance: Int = 75): Maze {
        if (height <= 0 || width <= 0)
            throw IllegalArgumentException("Sizes of maze must be positive integers")

        return generateMaze(height, width, wallChance)
    }

    public fun getMaze(side: Int, wallChance: Int = 75): Maze = getMaze(side, side, wallChance)


    private fun generateMaze(height: Int, width: Int, wallChance: Int): Maze {
        maze = Maze(height, width)

        val startCellCoordinates = CellCoordinates(Random.nextInt(height), Random.nextInt(width))

        val trace: Stack<CellCoordinates> = Stack()
        trace.add(startCellCoordinates)

        val visited = hashSetOf(startCellCoordinates)

        //start bfs
        while (trace.isNotEmpty()) {

            val currentCell = trace.pop()
            val nextCells = maze.getAdjacentCells(currentCell).filter { it !in visited }
            val nextCellsAssignment = getNextCellsAssignment(nextCells.size, wallChance)

            if (nextCellsAssignment.size != nextCells.size)
                print(1)

            //set up next cells types and update trace
            nextCellsAssignment.forEachIndexed { index, cellType ->
                val cell = nextCells[index]
                maze.setCellType(cell, cellType)


                visited.add(cell)
                if (cellType == CellType.PASSAGE)
                    trace.add(cell)
            }
        }
        return maze
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
}
