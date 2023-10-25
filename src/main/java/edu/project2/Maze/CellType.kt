package edu.project2.Maze

enum class CellType {
    PASSAGE {
        override fun getSymbol(): String = "⬛"
    },

    WALL {
        override fun getSymbol(): String = "⬜"
    },

    OUT_OF_BOUNDS {
        override fun getSymbol(): String = "*"
    },

    START {
        override fun getSymbol(): String = "\uD83D\uDFE6"
    },

    END {
        override fun getSymbol(): String = "\uD83D\uDFEA"
    },

    PATH {
        override fun getSymbol(): String = "🟥"
    };


    abstract fun getSymbol(): String


}
