package edu.hw3

import org.apache.logging.log4j.LogManager

class Task1 {
    private val lowers: MutableList<Char> = ArrayList()
    private val uppers: MutableList<Char> = ArrayList()
    private val lowersStart = 'a'.code
    private val uppersStart = 'A'.code

    private val LOGGER = LogManager.getLogger()

    init{
        for (i in 0..25) {
            lowers += (lowersStart+i).toChar()
            uppers += (uppersStart +i).toChar()
        }
    }

    fun atbash(s: String): String {
        val res = StringBuilder()
        for (i in s) {
            if (i.isLatinLower()) {
                res.append(lowers[25 - (i.code - lowersStart)])
            } else if (i.isLatinUpper()) {
                res.append(uppers[25 - (i.code - uppersStart)])
            } else {
                res.append(i)
            }
        }
        return res.toString()
    }

    //because 'ф'.isLetter() == true
    private fun Char.isLatinLower(): Boolean {
        return this in 'a'..'z'
    }

    private fun Char.isLatinUpper(): Boolean {
        return this in 'A'..'Z'
    }
}
