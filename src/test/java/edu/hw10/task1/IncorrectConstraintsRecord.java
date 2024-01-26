package edu.hw10.task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public record IncorrectConstraintsRecord(@Min(10)
                             @Max(-10)
                             int messedUpField) {
}
