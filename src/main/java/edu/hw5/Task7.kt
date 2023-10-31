package edu.hw5

class Task7 {

    public fun thirdSymbolIsZero(line: String): Boolean =
        line.matches("(0|1){2}0(0|1)*".toRegex())

    public fun haveSameStartAndEnd(line: String): Boolean =
        line.matches("^(0|1).*\\1".toRegex())

    public fun haveLengthInRange13(line: String): Boolean =
        line.matches("(0|1){1,3}".toRegex())

}
