package edu.hw3

class Task4 {


    private val alphabet: Map<Int, String> = mapOf(
        1000 to "M",
        500 to "D",
        100 to "C",
        50 to "L",
        10 to "X",
        5 to "V",
        1 to "I"
    )

    fun convertToRoman(num: Int): String {
        if (num !in 1..3999)
            throw IllegalArgumentException("num must be in [1;3999]")

        val numbers = listOf(1000, 500, 100, 50, 10, 5, 1)
        val cnt: MutableMap<Int, Int> = mutableMapOf(
            1000 to 0,
            500 to 0,
            100 to 0,
            50 to 0,
            10 to 0,
            5 to 0,
            1 to 0
        )

        var cpyNum = num
        for (number in alphabet.keys) {
            cnt[number] = cpyNum / number
            cpyNum %= number
        }


        return buildString {
            numbers.forEachIndexed { index, num ->
                append(
                    if (cnt[num] == 4)
                        alphabet[num] + alphabet[numbers[index - 1]]
                    else
                        alphabet[num]!!.repeat(cnt[num]!!)
                )
            }
        }
    }
}
