package edu.project2.Maze

enum class CellType private constructor(val symbol: String, val solution:Boolean) {
    PASSAGE("⬛",false),

    WALL("⬜",false),

    OUT_OF_BOUNDS("*",false),

    START("\uD83D\uDFE6",true),

    END("\uD83D\uDFEA",true),

    PATH("🟥",true);
}
