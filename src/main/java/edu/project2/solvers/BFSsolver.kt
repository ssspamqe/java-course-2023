package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze
import java.util.*

class BFSsolver {

    public fun solve(_maze: Maze, start: Cell, end: Cell):Maze {

        val maze = _maze.clone()

        if (maze.getCellType(start) != CellType.PASSAGE)
            throw IllegalArgumentException("Start cell is not a passage")

        if (maze.getCellType(end) != CellType.PASSAGE)
            throw IllegalArgumentException("End cell is not a passage")


        val queue:Queue<Cell> = LinkedList()

        queue.add(start)
        val ancestors = List(maze.height) { _ ->
            List(maze.width) { Cell(-1,-1) }.toMutableList()
        }

        val visited = List(maze.height) { _ ->
            List(maze.width) { false }.toMutableList()
        }


        while (queue.isNotEmpty()) {
            val currentCell = queue.poll()

            visited[currentCell.row][currentCell.column] = true

            val nextCells = getAdjacentCells(maze, currentCell).filter {
                !visited[it.row][it.column] && maze.getCellType(it) == CellType.PASSAGE
            }

            for (cell in nextCells) {
                ancestors[cell.row][cell.column] = currentCell
                queue.add(cell)

                if (cell.row == end.row && cell.column == end.column)
                    break
            }
        }


        var current = end
        while (!(current.row == start.row && current.column == start.column)) {
            current = ancestors[current.row][current.column]
            maze.setCellType(current, CellType.PATH)
        }

        maze.setCellType(start, CellType.START)
        maze.setCellType(end, CellType.END)

        return maze
    }

    private fun getAdjacentCells(maze: Maze, cell: Cell): List<Cell> {
        val adjacentCells = mutableListOf<Cell>()


        if (cell.row > 0) //up
            adjacentCells.add(Cell(cell.row - 1, cell.column))

        if (cell.column < maze.width - 1) //right
            adjacentCells.add(Cell(cell.row, cell.column + 1))

        if (cell.row < maze.height - 1) //down
            adjacentCells.add(Cell(cell.row + 1, cell.column))

        if (cell.column > 0)
            adjacentCells.add(Cell(cell.row, cell.column - 1))


        return adjacentCells
    }
}
