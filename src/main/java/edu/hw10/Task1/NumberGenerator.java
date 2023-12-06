package edu.hw10.Task1;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class NumberGenerator {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private NumberGenerator() {

    }

    public static Object generateNumberWithConstraints(
        ParameterConstraints constraints,
        Class<?> numberClass
    ) {
        if (isIntegral(numberClass)) {
            return generateIntegralNumberWithConstraints(constraints, numberClass);
        }
        return generateNonIntegralNumberWithConstraints(constraints, numberClass);
    }

    public static Number generateIntegralNumberWithConstraints(
        ParameterConstraints constraints,
        Class<?> numberClass
    ) {
        if (numberClass == byte.class || numberClass == Byte.class) {
            byte minValue = (byte) max(Byte.MIN_VALUE, constraints.min());
            byte maxValue = (byte) min(Byte.MAX_VALUE, constraints.max());
            return (byte) RANDOM.nextInt(minValue, maxValue);

        } else if (numberClass == short.class || numberClass == Short.class) {
            short minValue = (short) max(Short.MIN_VALUE, constraints.min());
            short maxValue = (short) min(Short.MAX_VALUE, constraints.max());
            return (short) RANDOM.nextInt(minValue, maxValue);

        } else if (numberClass == int.class || numberClass == Integer.class) {
            return RANDOM.nextInt((int) constraints.min(), (int) constraints.max());

        } else if (numberClass == long.class || numberClass == Long.class) {
            return RANDOM.nextLong((long) constraints.min(), (long) constraints.max());
        }

        throw new IllegalArgumentException("Given class does not represent integral number");
    }

    public static double generateNonIntegralNumberWithConstraints(
        ParameterConstraints constraints,
        Class<?> numberClass
    ) {
        if (numberClass == float.class || numberClass == Float.class) {
            return RANDOM.nextFloat((float) constraints.min(), (float) constraints.max());
        } else if(numberClass == double.class || numberClass == Double.class) {
            return RANDOM.nextDouble(constraints.min(), constraints.max());
        }
        throw new IllegalArgumentException("Given class does not represent a non integral number");

    }

    public static boolean isIntegral(Class<?> numberClass) {
        return !(numberClass == Double.class
            || numberClass == double.class
            || numberClass == Float.class
            || numberClass == float.class);
    }
}
