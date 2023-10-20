package edu.project2

import edu.project2.Maze.Cell
import edu.project2.generators.ChaoticMazeGenerator
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSsolver
import edu.project2.solvers.DFSsolver
import java.util.*



fun main() {
    val generator = IdealMazeGenerator()

    val maze = generator.getMaze(5,5)

    maze.printMaze(printBounds = true)
}
