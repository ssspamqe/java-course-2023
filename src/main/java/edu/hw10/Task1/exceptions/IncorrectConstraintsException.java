package edu.hw10.Task1.exceptions;

public class IncorrectConstraintsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "ObjectGenerator constraints: Min value must be less than or equal to Max value";
    }
}
