package edu.hw5

class Task7 {

    public fun String.thirdSymbolIsZero(): Boolean =
        this.matches("(0|1){2}0(0|1)*".toRegex())

    public fun String.haveSameStartAndEnd(): Boolean =
        this.matches("^(0|1).*\\1".toRegex())

    public fun String.haveLengthInRange13():Boolean =
        this.matches(".{1,3}".toRegex())
}
