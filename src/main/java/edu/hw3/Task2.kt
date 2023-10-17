package edu.hw3

class Task2 {

    fun clusterize(s: String): List<String> {

        var res:MutableList<String> = mutableListOf()

        var cnt = 0;
        var lastCluster = ""

        s.forEach {
            lastCluster += it

            cnt += (it == '(').toInt()

            if (cnt == 0) {
                res += lastCluster
                lastCluster = ""
            }
        }

        return res
    }

    private fun Boolean.toInt() = if (this) 1 else -1

}
