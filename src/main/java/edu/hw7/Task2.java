package edu.hw7;

import jakarta.validation.constraints.Positive;
import java.util.stream.IntStream;

public class Task2 {

    public static int getFactorial(@Positive int num) {
        return IntStream
            .rangeClosed(1, num)
            .parallel()
            .reduce((a, b) -> a * b)
            .getAsInt();
    }

}
