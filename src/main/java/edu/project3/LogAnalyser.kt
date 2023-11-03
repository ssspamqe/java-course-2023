package edu.project3

class LogAnalyser {

    fun getRequestsAmount(logs: List<Map<String, String>>): Int =
        logs.size


    fun getTheMostPopularResources(
        logs: List<Map<String, String>>,
        amount: Int = -1,
        request: String = "GET"
    ): Map<String?, Int> =
        logs
            .filter { it["request_type"] == request }
            .map { it["request"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .subList(0, amount)
            .toMap()


    fun getTheMostPopularStatuses(
        logs: List<Map<String, String>>,
        amount: Int = -1,
    ): Map<String?, Int> =
        logs
            .map { it["status"] }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.second }
            .subList(0, amount)
            .toMap()
}
