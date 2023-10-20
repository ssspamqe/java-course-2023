package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

class DFSsolver : MazeSolver {

    private lateinit var maze: Maze
    private var visited = hashSetOf<Cell>()
    private lateinit var ancestors: List<MutableList<Cell>>

    override fun solve(mazeParam: Maze, start: Cell, end: Cell): Maze {

        maze = mazeParam.clone()

        ancestors = List(maze.height) { _ ->
            List(maze.width) { Cell(-1, -1) }.toMutableList()
        }

        dfs(start)

        return buildSolvedMaze(maze, start, end, ancestors)
    }

    private fun dfs(cell: Cell) {

        visited.add(cell)

        val nextCells = maze.getAdjacentCells(cell).filter {
            it !in visited && maze.getCellType(cell) == CellType.PASSAGE
        }


        nextCells.forEach {
            ancestors[it.row][it.column] = cell
            dfs(it)
        }
    }
}
