package edu.project2.Maze

enum class CellType private constructor(public val symbol: String) {
    PASSAGE("⬛"),

    WALL("⬜"),

    OUT_OF_BOUNDS("*"),

    START("\uD83D\uDFE6"),

    END("\uD83D\uDFEA"),

    PATH("🟥");
}
