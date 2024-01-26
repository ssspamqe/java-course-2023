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
