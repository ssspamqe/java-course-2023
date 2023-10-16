package edu.hw3

class Task2 {

    fun clusterize(s: String): List<String> {

        var res: List<String> = emptyList();

        var cnt = 0;
        var lastCluster = "";
        s.forEach {
            lastCluster += it;

            if (it == '(')
                cnt++;
            else
                cnt--;

            if (cnt == 0) {
                res += lastCluster;
                lastCluster = "";
            }
        }

        return res;
    }

}
