package edu.project2.solvers

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

class DFSSolver : MazeSolver() {

    private lateinit var maze: Maze
    private lateinit var visited: HashSet<CellCoordinates>
    private lateinit var ancestors: List<List<CellCoordinates>>

    override fun solve(mazeParam: Maze, start: CellCoordinates, end: CellCoordinates): Maze {
        maze = mazeParam.clone()

        if (maze.getCellType(start) != CellType.PASSAGE)
            throw IllegalArgumentException("Start cell is not a passage")

        if (maze.getCellType(end) != CellType.PASSAGE)
            throw IllegalArgumentException("End cell is not a passage")

        ancestors = List(maze.height) { _ ->
            List(maze.width) { CellCoordinates(-1, -1) }
        }

        visited = hashSetOf()


        dfs(start)

        return buildSolvedMaze(maze, start, end, ancestors)
    }

    private fun dfs(cellCoordinates: CellCoordinates) {

        visited.add(cellCoordinates)

        val nextCells = maze.getAdjacentCells(cellCoordinates).filter {
            it !in visited && maze.getCellType(cellCoordinates) == CellType.PASSAGE
        }


        nextCells.forEach {
            (ancestors[it.row] as MutableList<CellCoordinates>)[it.column] = cellCoordinates
            dfs(it)
        }
    }
}
