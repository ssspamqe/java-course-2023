package edu.project2

import edu.project2.generators.chaotic.ChaoticMazeGenerator

data class Person(val name: String, val age: Int)

fun main() {
    val generator = ChaoticMazeGenerator()

    val maze = generator.getMaze(70,70,80)

    maze.printMaze()
}
