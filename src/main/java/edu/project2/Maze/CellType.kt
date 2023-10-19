package edu.project2.Maze

enum class CellType {
    PASSAGE {
        override fun getSymbol(): Char = ' '
    },

    WALL {
        override fun getSymbol(): Char = '■'
    },

    OUT_OF_BOUNDS {
        override fun getSymbol(): Char = '*'
    },

    START {
        override fun getSymbol(): Char = 's'
    },

    END {
        override fun getSymbol(): Char = 'e'
    },

    PATH {
        override fun getSymbol(): Char = '.'
    };


    abstract fun getSymbol(): Char


}
