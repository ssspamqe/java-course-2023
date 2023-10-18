package edu.project2.Maze

import java.util.Stack
import kotlin.random.Random

class IdealMazeGenerator() {

    data class Cell(var row:Int, var column:Int)

    private var height:Int = 0
    private var width:Int = 0

    private var maze: Maze? = null

    public fun getMaze(height: Int, width: Int):Maze {
        this.height = height
        this.width = width
        return generateMaze(height, width)
    }

    public fun getMaze(size: Int):Maze {
        return generateMaze(size,size)
    }

    private fun generateMaze(height: Int, width: Int):Maze {

        maze = Maze(height,width)


        val startCell = Cell(Random.nextInt(height), Random.nextInt(width))


        val trace:Stack<Cell> = Stack()
        trace.add(startCell)
        val visited = hashSetOf(startCell)


        while(trace.isNotEmpty()){

            val currentCell = trace.pop()

            val nextCells = getAdjacentCells(currentCell).filter { it !in visited }
            val nextCellsAssignment = getNextCellsAssignment(nextCells.size,50)

            if(nextCellsAssignment.size!=nextCells.size)
                print(1)

            //set up next cells types
            nextCellsAssignment.forEachIndexed { index, cellType ->
                val cell = nextCells[index]
                maze!!.setCell(cell.row,cell.column,cellType)
            }


            nextCells.forEach {
                visited.add(it)
                trace.add(it)
            }
        }

        return maze as Maze
    }

    private fun getNextCellsAssignment(amount:Int, wallChance:Int):List<CellType>{

        if(amount==0)
            return emptyList()

        if(wallChance !in 0..100)
            throw IllegalArgumentException("wallChance must be in range [0;100]")

        val assignment = mutableListOf(CellType.PASSAGE)

        while(assignment.size < amount)
        {
            val randInt = Random.nextInt(1,101)

            if(randInt <= wallChance)
                assignment.add(CellType.WALL)
            else
                assignment.add(CellType.PASSAGE)
        }

        assignment.shuffle()
        return assignment
    }


    private fun getAdjacentCells(cell:Cell):List<Cell>{
        val adjacentCells = mutableListOf<Cell>()


        if(cell.row>0) //up
            adjacentCells.add(Cell(cell.row-1,cell.column))

        if(cell.column<width-1) //right
            adjacentCells.add(Cell(cell.row, cell.column+1))

        if(cell.row < height-1) //down
            adjacentCells.add(Cell(cell.row+1, cell.column))

        if(cell.column > 0)
            adjacentCells.add(Cell(cell.row, cell.column-1))


        return adjacentCells
    }




}
