package edu.project2.solvers.asyncDFSSolver

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.Maze
import java.util.concurrent.RecursiveTask

internal class AsyncDFSProcedure(
    private val currentCoordinates: CellCoordinates,
    private val path: Map<CellCoordinates, CellCoordinates>,
    private val visited: Set<CellCoordinates>,
    private val finish: CellCoordinates,
    private val maze: Maze
) : RecursiveTask<Map<CellCoordinates, CellCoordinates>?>() {
    
    override fun compute(): Map<CellCoordinates, CellCoordinates>? {
        if (currentCoordinates == finish) {
            return path;
        }

        val adjacentCells = maze.getAdjacentCells(currentCoordinates)

        val forks = mutableListOf<AsyncDFSProcedure>()
        adjacentCells.forEach {
            if (!visited.contains(it)) {
                forks.add(
                    AsyncDFSProcedure(
                        it,
                        path.plus(currentCoordinates to it),
                        visited.plusElement(currentCoordinates),
                        finish,
                        maze
                    )
                )
            }
        }

        if (forks.isEmpty())
            return null;

        return getShortestPath(forks)
    }

    private fun getShortestPath(forks: List<AsyncDFSProcedure>): Map<CellCoordinates, CellCoordinates>? {
        var completePath: Map<CellCoordinates, CellCoordinates>? = null
        forks.forEach { fork ->
            val path = fork.join()
            if (path != null) {
                if (completePath == null) {
                    completePath = path
                } else if (path.size < completePath!!.size) {
                    completePath = path
                }
            }
        }
        return completePath
    }


}
