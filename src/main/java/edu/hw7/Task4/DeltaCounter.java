package edu.hw7.Task4;

import java.util.function.IntToDoubleFunction;
import java.util.function.Supplier;

public class DeltaCounter {

    public static double getAverageDelta(int iterations, double original, Supplier<Double> supplierFunction) {
        double deltaSum = 0;

        for (int i = 0; i < iterations; i++) {
            double val = supplierFunction.get();
            double delta = Math.abs(val - original);
            deltaSum += delta;
        }

        return deltaSum / iterations;
    }

//    public static double getAverageDelta(
//        int iterations,
//        double original,
//        IntToDoubleFunction intToDoubleFunction,
//        int param
//    ) {
//        double deltaSum = 0;
//
//        for (int i = 0; i < iterations; i++) {
//            double val = intToDoubleFunction.applyAsDouble(param);
//            double delta = Math.abs(val - original);
//            deltaSum += delta;
//        }
//
//        return deltaSum / iterations;
//    }

}
