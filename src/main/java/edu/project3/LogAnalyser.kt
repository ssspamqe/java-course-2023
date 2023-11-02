package edu.project3

class LogAnalyser {

    fun getRequestsAmount(logs:List<Map<String,String>>):Int =
        logs.size


    fun getTheMostPopularGETResources(logs:List<Map<String,String>>):List<String>{

        val occurrences = logs
            .filter { it["request_type"] == "GET" }
            .map { it["request"] }
            .groupingBy { it }
            .eachCount()

        val max = occurrences.map { it.value }.max()

        return occurrences.filter { it.value == max }.map { it.key!! }.toList()
    }

    fun getTheMostPopularStatuses(logs:List<Map<String,String>>):List<String>{

        val occurrences = logs
            .map{it["status"]}
            .groupingBy { it }
            .eachCount()

        val max = occurrences.map{it.value}.max()

        return occurrences.filter { it.value == max }.map { it.key!! }.toList()

    }
}
