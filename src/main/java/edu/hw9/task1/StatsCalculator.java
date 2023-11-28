package edu.hw9.task1;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class StatsCalculator {

    public static <T extends Number> double getMetric(MetricType type, T[] array) {
        return switch (type) {
            case MIN -> getMin(array);
            case MAX -> getMax(array);
            case AVERAGE -> getAverage(array);
            case SUM -> getSum(array);
        };
    }

    public static <T extends Number> double getSum(T[] array) {
        return getDoubleStream(array)
            .sum();
    }

    public static <T extends Number> double getAverage(T[] array) {
        return getDoubleStream(array)
            .average()
            .orElseThrow();
    }

    public static <T extends Number> double getMax(T[] array) {
        return getDoubleStream(array)
            .max()
            .orElseThrow();
    }

    public static <T extends Number> double getMin(T[] array) {
        return getDoubleStream(array)
            .min()
            .orElseThrow();
    }

    private static <T extends Number> DoubleStream getDoubleStream(T[] array) {
        return Arrays.stream(array).mapToDouble(Number::doubleValue);
    }
}
