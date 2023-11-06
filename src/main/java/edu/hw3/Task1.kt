package edu.hw3

class Task1 {
    private val lowers: MutableList<Char> = ArrayList()
    private val uppers: MutableList<Char> = ArrayList()
    private val lowersStart = 'a'.code
    private val uppersStart = 'A'.code

    init {
        for (i in 0..25) {
            lowers += (lowersStart + i).toChar()
            uppers += (uppersStart + i).toChar()
        }
    }

    fun atbash(s: String): String =
        buildString {
            for (i in s)
                append(
                    if (i.isLatinLower())
                        lowers[25 - (i.code - lowersStart)]
                    else if (i.isLatinUpper())
                        uppers[25 - (i.code - uppersStart)]
                    else
                        i
                )
        }


    //because 'ф'.isLetter() == true
    private fun Char.isLatinLower(): Boolean {
        return this in 'a'..'z'
    }

    private fun Char.isLatinUpper(): Boolean {
        return this in 'A'..'Z'
    }
}
