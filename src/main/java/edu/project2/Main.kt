package edu.project2

import edu.project2.Maze.IdealMazeGenerator
import edu.project2.Maze.Maze
import org.apache.logging.log4j.LogManager

data class Person(val name: String, val age: Int)

fun main() {
    val generator = IdealMazeGenerator()

    val maze = generator.getMaze(10)

    maze.printMaze()
}
