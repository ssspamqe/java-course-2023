package edu.hw5


//optional task
class Task8 {

    //нечетной длины
    public fun check0(line:String):Boolean =
        line.matches("(0|1)(?:(0|1){2})*".toRegex())


    //начинается с 0 и имеет нечетную длину,
    // или начинается с 1 и имеет четную длину
    public fun check1(line:String):Boolean =
        line.matches("0(?:(0|1){2})*".toRegex()) || line.matches("1(0|1)(?:(0|1){2})*".toRegex())


    //каждый нечетный символ равен 1
    public fun check2(line:String):Boolean=
        line.matches("(?:1(1|0)?)+".toRegex())

    //нет последовательных 1
    public fun check3(line:String):Boolean =
        !line.matches("(0|1)*11(0|1)".toRegex())
}
