package edu.project2.solvers

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

class DFSSolver : MazeSolver() {

    private lateinit var maze: Maze
    private lateinit var visited: Set<CellCoordinates>
    private lateinit var ancestors: Map<CellCoordinates, CellCoordinates>

    override fun solve(mazeParam: Maze, start: CellCoordinates, end: CellCoordinates): Maze {
        maze = mazeParam.clone()

        if (maze.getCellType(start) != CellType.PASSAGE)
            throw IllegalArgumentException("Start cell is not a passage")

        if (maze.getCellType(end) != CellType.PASSAGE)
            throw IllegalArgumentException("End cell is not a passage")

        ancestors = hashMapOf()
        visited = hashSetOf()

        dfs(start)

        return buildSolvedMaze(maze, start, end, ancestors)
    }

    private fun dfs(cellCoordinates: CellCoordinates) {
        (visited as HashSet<CellCoordinates>).add(cellCoordinates)

        val nextCells = maze.getAdjacentCells(cellCoordinates).filter {
            it !in visited && maze.getCellType(cellCoordinates) == CellType.PASSAGE
        }


        nextCells.forEach {
            (ancestors as HashMap<CellCoordinates, CellCoordinates>)[it] = cellCoordinates
            dfs(it)
        }
    }
}
