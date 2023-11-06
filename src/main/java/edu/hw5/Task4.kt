package edu.hw5

class Task4 {

    public fun validatePassword(password: String): Boolean =
        password.matches(".*[~!@#\$%^&*|].*".toRegex())
}
