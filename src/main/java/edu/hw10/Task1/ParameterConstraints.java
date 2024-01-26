package edu.hw10.Task1;

import edu.hw10.Task1.exceptions.IncorrectConstraintsException;

public record ParameterConstraints(boolean notNull,
                                   double min,
                                   double max) {
    public ParameterConstraints {
        if (min > max) {
            throw new IncorrectConstraintsException();
        }
    }
}

