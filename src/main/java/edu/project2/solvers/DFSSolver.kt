package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

class DFSSolver : MazeSolver() {

    private lateinit var maze: Maze
    private lateinit var visited:HashSet<Cell>
    private lateinit var ancestors: List<List<Cell>>

    override fun solve(mazeParam: Maze, start: Cell, end: Cell): Maze {
        maze = mazeParam.clone()

        if (maze.getCellType(start) != CellType.PASSAGE)
            throw IllegalArgumentException("Start cell is not a passage")

        if (maze.getCellType(end) != CellType.PASSAGE)
            throw IllegalArgumentException("End cell is not a passage")

        ancestors = List(maze.height) { _ ->
            List(maze.width) { Cell(-1, -1) }
        }

        visited = hashSetOf()


        dfs(start)

        return buildSolvedMaze(maze, start, end, ancestors)
    }

    private fun dfs(cell: Cell) {

        visited.add(cell)

        val nextCells = maze.getAdjacentCells(cell).filter {
            it !in visited && maze.getCellType(cell) == CellType.PASSAGE
        }


        nextCells.forEach {

            val mutableAncestors = ancestors.toMutableList()                        //?????
            mutableAncestors[it.row] = mutableAncestors[it.row].toMutableList()    //??????
            (mutableAncestors[it.row] as MutableList<Cell>)[it.column] = cell         //?????
            mutableAncestors[it.row] = mutableAncestors[it.row].toList()            //????

            ancestors = mutableAncestors.toList()
            dfs(it)
        }
    }
}
