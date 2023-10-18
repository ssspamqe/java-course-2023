package edu.project2.Maze

enum class CellType {
    PASSAGE{
        override fun getSymbol():Char = '□'
    },

    WALL{
        override fun getSymbol(): Char = '■'
    };

    abstract fun getSymbol():Char

}
