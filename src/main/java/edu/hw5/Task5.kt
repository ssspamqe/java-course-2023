package edu.hw5

class Task5 {

    public fun validateCarPlate(plate:String):Boolean =
        plate.matches("[A-Z]\\d{3}[A-Z]{2}\\d{3}".toRegex())
}
