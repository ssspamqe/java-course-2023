package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

interface MazeSolver {
    public fun solve(mazeParam: Maze, start: Cell, end: Cell): Maze
    fun buildSolvedMaze(maze: Maze, start: Cell, end: Cell, ancestors: List<MutableList<Cell>>): Maze {

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
