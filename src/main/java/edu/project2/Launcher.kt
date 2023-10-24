package edu.project2

import edu.project2.Maze.Cell
import edu.project2.Maze.Maze
import edu.project2.generators.MazeGenerator
import edu.project2.generators.chaoticMaze.ChaoticMazeGenerator
import edu.project2.generators.idealMaze.IdealMazeGenerator
import edu.project2.solvers.BFSSolver
import edu.project2.solvers.DFSSolver
import edu.project2.solvers.MazeSolver
import org.apache.logging.log4j.LogManager
import java.util.*

class Launcher {

    val LOGGER = LogManager.getLogger()
    lateinit var sc: Scanner


    var height: Int = -1
    var width: Int = -1
    var printBounds: Boolean = false
    var wallChance: Int = 50


    var generator: MazeGenerator = IdealMazeGenerator()
    var maze: Maze = Maze(0,0)
    var solvedMaze: Maze = Maze(0,0)
    var solver: MazeSolver = BFSSolver()


    var start = Cell(0, 0)
    var end = Cell(0, 0)


    var choice: Char = 'a'

    fun startGame() {

        sc = Scanner(System.`in`)

        while (choice != 'n') {
            LOGGER.info(
                """WELCOME TO MAZE GENERATOR!
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


            LOGGER.info(
                """
        Select maze solving algorithm:
            1 for bfs
            2 for dfs
        """
            )
            var solverType = -1
            while (solverType !in 1..2) {
                LOGGER.info("type either '1' or '2'")
                solverType = sc.nextInt()
            }

            solver =
                if (mazeType == 1) {
                    LOGGER.info("You've chosen bfs")
                    BFSSolver()
                } else {
                    LOGGER.info("You've chosen dfs")
                    DFSSolver()
                }


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

        start = Cell(row, column)


        LOGGER.info("Input row of end cell")
        row = sc.nextInt()

        LOGGER.info("Input column of end cell")
        column = sc.nextInt()

        end = Cell(row, column)
    }

}
