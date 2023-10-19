package edu.project2

import edu.project2.Maze.Cell
import edu.project2.generators.ChaoticMazeGenerator
import edu.project2.solvers.DFSsolver
import java.util.*

data class Person(val name: String, val age: Int)

fun main() {
    val generator = ChaoticMazeGenerator()

    val maze = generator.getMaze(20, 20, 95)

    maze.printMaze()


    val sc = Scanner(System.`in`)
    val a = sc.nextInt()
    val b = sc.nextInt()

    val c = sc.nextInt()
    val d = sc.nextInt()

    sc.close()

    val solver = DFSsolver()
    val solvedMaze = solver.solve(maze, Cell(a, b), Cell(c, d))

    solvedMaze.printMaze()
}
