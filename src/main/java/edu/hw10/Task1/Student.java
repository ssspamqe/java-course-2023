package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public record Student(@Min(10) @Max(20) int group ,@Min(18) @Max(36) int age) {
}
