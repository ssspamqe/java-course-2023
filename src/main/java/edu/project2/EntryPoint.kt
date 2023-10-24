package edu.project2

import edu.project2.Maze.Cell
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSSolver

fun main(){
    val generator = IdealMazeGenerator()
    val maze = generator.getMaze(20)
    val solver = BFSSolver()
    maze.printMaze(true)

    val solvedMaze = solver.solve(maze,Cell(0,0), Cell(18,18))

    maze.printMaze(true)

}
