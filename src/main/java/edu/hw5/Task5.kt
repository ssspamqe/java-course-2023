package edu.hw5

class Task5 {

    public fun validateCarPlate(plate:String):Boolean =
        plate.matches("\\w\\d{3}\\w{2}\\d{3}".toRegex())
}
