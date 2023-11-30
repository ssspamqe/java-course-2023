package edu.project2.solvers

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import org.apache.logging.log4j.LogManager

abstract class MazeSolver {
    private val LOGGER = LogManager.getLogger()
    public abstract fun solve(mazeParam: Maze, start: CellCoordinates, end: CellCoordinates): Maze
    protected fun buildSolvedMaze(
        maze: Maze,
        start: CellCoordinates,
        end: CellCoordinates,
        ancestors: Map<CellCoordinates, CellCoordinates>
    ): Maze {
        var currentCell = end

        while (currentCell != start) {
            if(ancestors[currentCell] == null){
                LOGGER.error("Given path was not completed")
                break
            }
            currentCell = ancestors[currentCell]!!
            maze.setCellType(currentCell, CellType.PATH)
        }

        maze.setCellType(start, CellType.START)
            .setCellType(end, CellType.END)

        return maze;
    }
}
