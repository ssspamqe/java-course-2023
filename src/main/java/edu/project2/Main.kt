package edu.project2

import edu.project2.generators.idealMaze.IdealMazeGenerator


fun main() {
    val generator = IdealMazeGenerator()

    val maze = generator.getMaze(99,99)

    maze.printMaze(printBounds = true)
}
