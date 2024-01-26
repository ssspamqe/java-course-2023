package edu.hw10.task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public record RecordSample(@Min(-1000) @Max(1000) int sampleInt,
                           @Min(-1000) @Max(1000) int sampleFloat,
                           boolean sampleBoolean,
                           char sampleChar) {
}
