package edu.hw5

class Task6 {

    public fun containsString(superString: String, subString:String):Boolean =
        superString.contains(subString)


    public fun String.contains(subString:String):Boolean =
        this.matches(".*${subString}.*".toRegex())


}
