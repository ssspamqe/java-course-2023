package edu.project2.solvers.asyncDFSSolver

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.Maze
import edu.project2.solvers.MazeSolver
import java.util.concurrent.ForkJoinPool

class AsyncDFSSolver : MazeSolver() {
    override fun solve(maze: Maze, start: CellCoordinates, end: CellCoordinates): Maze {
        val dfs = AsyncDFSProcedure(start, listOf(), setOf(), end, maze)
        val forkJoinPool = ForkJoinPool()
        val path = forkJoinPool.invoke(dfs)

        return buildSolvedMaze(maze,start,end,path)
    }


}
