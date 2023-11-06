package edu.hw5

class Task6 {

    public fun containsSubsequence(superString: String, subString: String): Boolean =
        superString.contains(subString)


    public fun String.contains(subSequence: String): Boolean {
        val regex = buildString {
            append(".*")
            append(subSequence.toList().joinToString(".*"))
        }.toRegex()
        return this.matches(regex)
    }


}
