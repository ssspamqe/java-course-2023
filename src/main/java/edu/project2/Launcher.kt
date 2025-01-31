package edu.project2

import edu.project2.Maze.CellCoordinates
import edu.project2.Maze.Maze
import edu.project2.generators.MazeGenerator
import edu.project2.generators.chaoticMaze.ChaoticMazeGenerator
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSSolver
import edu.project2.solvers.DFSSolver
import edu.project2.solvers.MazeSolver
import edu.project2.solvers.asyncDFSSolver.AsyncDFSSolver
import org.apache.logging.log4j.LogManager
import java.util.*

fun main() {
    val l = Launcher()
    l.startGame()
}

class Launcher {

    private val LOGGER = LogManager.getLogger()
    private lateinit var sc: Scanner


    private var height: Int = -1
    private var width: Int = -1
    private var printBounds: Boolean = false
    private var wallChance: Int = 50


    private var generator: MazeGenerator = IdealMazeGenerator()
    private var maze: Maze = Maze(0, 0)
    private var solvedMaze: Maze = Maze(0, 0)
    private var solver: MazeSolver = BFSSolver()


    private var start = CellCoordinates(0, 0)
    private var end = CellCoordinates(0, 0)


    private var choice: Char = 'a'

    fun startGame() {
        sc = Scanner(System.`in`)
        LOGGER.info("WELCOME TO MAZE GENERATOR!")

        while (choice != 'n') {

            chooseMazeGenerator()

            chooseSolvingAlgo()

            getStartEndCells()

            solvedMaze = solver.solve(maze, start, end)
            solvedMaze.printMaze(printBounds)

            LOGGER.info("Do you want to finish? (y/n)")

            sc.nextLine()
            choice = sc.nextLine()[0]
            while (choice !in "yn")
                choice = sc.nextLine()[0]

            if (choice == 'y')
                break
        }

        sc.close()
    }

    private fun setUpChaoticMaze() {
        generator = ChaoticMazeGenerator()

        getDefaultMazeInfo()

        LOGGER.info(
            "Input integer from [0;100] that is chance of getting wall " +
                "(the less number - the less walls in the maze)"
        )
        wallChance = sc.nextInt()

        maze = (generator as ChaoticMazeGenerator).getMaze(height, width, wallChance)

        maze.printMaze(printBounds)

        LOGGER.info("Do you want to regenerate it? (y/n)")
        sc.nextLine()
        choice = sc.nextLine()[0]
        while (choice !in "yn")
            choice = sc.nextLine()[0]

        if (choice == 'y')
            setUpIdealMaze()
    }

    private fun setUpIdealMaze() {
        generator = IdealMazeGenerator()

        getDefaultMazeInfo()

        maze = (generator as IdealMazeGenerator).getMaze(height, width)

        maze.printMaze(printBounds)

        LOGGER.info("Do you want to regenerate it? (y/n)")
        choice = sc.nextLine()[0]
        while (choice !in "yn")
            choice = sc.nextLine()[0]

        if (choice == 'y')
            setUpIdealMaze()
    }

    private fun getDefaultMazeInfo() {
        LOGGER.info("Input height of the maze")
        height = sc.nextInt()

        LOGGER.info("Input width of the maze")
        width = sc.nextInt()

        LOGGER.info("Do you want to print bounds of your maze? (y/n)")
        sc.nextLine()
        var choice: Char = sc.nextLine()[0]
        while (choice !in "yn")
            choice = sc.nextLine()[0]

        printBounds =
            if (choice == 'y')
                true
            else
                false
    }

    private fun getStartEndCells() {

        LOGGER.info(
            """
        Now you need to input coordinates of starting and ending points
        The start of counting is in upper left corner (not counting bound's corner)
        Enumeration starts with zero
        """
        )

        LOGGER.info("Input row of start cell")
        var row: Int = sc.nextInt()

        LOGGER.info("Input column of start cell")
        var column: Int = sc.nextInt()

        start = CellCoordinates(row, column)


        LOGGER.info("Input row of end cell")
        row = sc.nextInt()

        LOGGER.info("Input column of end cell")
        column = sc.nextInt()

        end = CellCoordinates(row, column)
    }

    private fun chooseMazeGenerator() {
        LOGGER.info(
            """
        Select maze generating algorithm:
            1 for chaotic maze
            2 for ideal maze
        """
        )

        var mazeType = -1

        while (mazeType !in 1..2) {
            LOGGER.info("type either '1' or '2'")
            mazeType = sc.nextInt()
        }

        if (mazeType == 1) {
            LOGGER.info("You've chosen chaotic maze")
            setUpChaoticMaze()
        } else {
            LOGGER.info("You've chosen ideal maze")
            setUpIdealMaze()
        }

    }

    private fun chooseSolvingAlgo() {
        LOGGER.info(
            """
        Select maze solving algorithm:
            1 for bfs
            2 for dfs
            3 for async dfs
        """
        )
        var solverType = -1
        while (solverType !in 1..3) {
            LOGGER.info("type integer in [1;3]")
            solverType = sc.nextInt()
        }

        solver = when (solverType) {
            1 -> {
                LOGGER.info("You've chosen bfs")
                BFSSolver()
            }

            2 -> {
                LOGGER.info("You've chosen dfs")
                DFSSolver()
            }

            3 -> {
                LOGGER.info("You've chosen async dfs")
                AsyncDFSSolver()
            }

            else -> {
                LOGGER.info("You've chosen bfs")
                BFSSolver()
            }
        }
    }
}
