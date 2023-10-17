package edu.hw3

class Task3 {

    fun <T> freqDict(list: List<T>): Map<T, Int> {

        val res = hashMapOf<T, Int>()

        list.forEach {
            if (it !in res.keys)
                res[it] = 0
            res[it] = res[it]!! + 1
        }

        return res
    }

}
