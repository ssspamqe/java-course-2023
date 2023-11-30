package edu.project2.solvers.asyncDFSSolver

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.Maze
import org.apache.logging.log4j.LogManager
import java.util.concurrent.RecursiveTask

internal class AsyncDFSProcedure(
    private val currentCoordinates: CellCoordinates,
    private val path: Map<CellCoordinates, CellCoordinates>,
    private val visited: Set<CellCoordinates>,
    private val finish: CellCoordinates,
    private val maze: Maze
) : RecursiveTask<Map<CellCoordinates, CellCoordinates>?>() {

    private val LOGGER = LogManager.getLogger()

    override fun compute(): Map<CellCoordinates, CellCoordinates>? {
        if (currentCoordinates == finish) {
            return path;
        }

        val adjacentPassages = maze.getAdjacentPassages(currentCoordinates)
        val forks = getForkedChildren(adjacentPassages)

        if (forks.isEmpty())
            return null;

        return getShortestPath(forks)
    }

    private fun getForkedChildren(adjacentPassages: List<CellCoordinates>): List<AsyncDFSProcedure> {
        val forks = mutableListOf<AsyncDFSProcedure>()
        adjacentPassages.forEach {
            if (!visited.contains(it)) {
                forks.add(
                    AsyncDFSProcedure(
                        it,
                        path.plus(it to currentCoordinates),
                        visited.plusElement(currentCoordinates),
                        finish,
                        maze
                    )
                )
                forks.last.fork()
            }
        }
        return forks
    }

    private fun getShortestPath(forks: List<AsyncDFSProcedure>): Map<CellCoordinates, CellCoordinates>? {
        var completePath: Map<CellCoordinates, CellCoordinates>? = null
        forks.forEach { fork ->
            val path = fork.join()
            if (path != null) {
                if (completePath == null || path.size < completePath!!.size) {
                    completePath = path
                }
            }
        }
        return completePath
    }
}
