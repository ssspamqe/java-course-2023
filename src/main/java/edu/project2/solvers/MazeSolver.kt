package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

interface MazeSolver {
    public fun solve(mazeParam: Maze, start: Cell, end: Cell):Maze

    fun getAdjacentCells(maze: Maze, cell: Cell): List<Cell> {
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

    fun buildSolvedMaze(maze:Maze, start:Cell, end:Cell, ancestors:List<MutableList<Cell>>):Maze{
        var currentCell = end

        while (!(currentCell.row == start.row && currentCell.column == start.column)) {
            currentCell = ancestors[currentCell.row][currentCell.column]
            maze.setCellType(currentCell, CellType.PATH)
        }

        maze.setCellType(start, CellType.START)
            .setCellType(end, CellType.END)


        return maze;
    }

}
