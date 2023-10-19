package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

class DFSsolver : MazeSolver {

    private lateinit var maze: Maze
    private lateinit var visited: List<MutableList<Boolean>>
    private lateinit var ancestors: List<MutableList<Cell>>

    override fun solve(mazeParam: Maze, start: Cell, end: Cell): Maze {

        maze = mazeParam.clone()

        visited = List(maze.height) { _ ->
            List(maze.width) { false }.toMutableList()
        }

        ancestors = List(maze.height) { _ ->
            List(maze.width) { Cell(-1, -1) }.toMutableList()
        }

        dfs(start)

        return buildSolvedMaze(maze, start, end, ancestors)
    }

    private fun dfs(cell: Cell) {

        visited[cell.row][cell.column] = true

        val nextCells = getAdjacentCells(maze, cell).filter {
            !visited[it.row][it.column] && maze.getCellType(cell) == CellType.PASSAGE
        }


        nextCells.forEach {
            ancestors[it.row][it.column] = cell
            dfs(it)
        }
    }
}
