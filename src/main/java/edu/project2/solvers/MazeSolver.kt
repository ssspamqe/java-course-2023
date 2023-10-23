package edu.project2.solvers

import edu.project2.Maze.Cell
import edu.project2.Maze.CellType
import edu.project2.Maze.Maze

abstract class MazeSolver {
    public abstract fun solve(mazeParam: Maze, start: Cell, end: Cell): Maze
    internal fun buildSolvedMaze(maze: Maze, start: Cell, end: Cell, ancestors: List<List<Cell>>): Maze {

        var currentCell = end

        while (currentCell!=start) {
            currentCell = ancestors[currentCell.row][currentCell.column]

            if(maze.getCellType(currentCell) == CellType.OUT_OF_BOUNDS)
                print(1)

            maze.setCellType(currentCell, CellType.PATH)
        }

        maze.setCellType(start, CellType.START)
            .setCellType(end, CellType.END)

        return maze;
    }
}
