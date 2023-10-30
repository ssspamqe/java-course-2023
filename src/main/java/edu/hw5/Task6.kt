package edu.hw5

class Task6 {

    public fun containsString(superString: String, subString:String):Boolean{
        return superString.contains(subString)
    }

    private fun String.contains(subString:String):Boolean{
        return this.matches(".*${subString}.*".toRegex())
    }

}
