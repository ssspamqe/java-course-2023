package edu.hw7.Task4.simulation;

import edu.hw7.Task4.PiCalculator;

public class Simulator {

    PiCalculator calculator = new PiCalculator();

    public SimulationResult getSingleThreadResult(int simulations, double original, int randomPointsAmount) {
        double executingTimeSum = 0;
        double deltaSum = 0;


        for (int i = 0; i < simulations; i++) {
            var startTime = System.nanoTime();
            double val = calculator.getPi(randomPointsAmount);
            var endTime = System.nanoTime();

            double delta = Math.abs(val - original);

            executingTimeSum += convertNanosecondsToMilliseconds(endTime - startTime);
            deltaSum += delta;
        }

        double averageExecutingTime = executingTimeSum / simulations;
        double averageDelta = deltaSum / simulations;

        return new SimulationResult(averageExecutingTime, averageDelta);
    }

    public SimulationResult getMultiThreadResult(int simulations, double original, int randomPointsPerThread, int threadsAmount) {
        double executingTimeSum = 0;
        double deltaSum = 0;


        for (int i = 0; i < simulations; i++) {
            var startTime = System.nanoTime();
            double val = calculator.getPiAsync(threadsAmount,randomPointsPerThread);
            var endTime = System.nanoTime();

            double delta = Math.abs(val - original);

            executingTimeSum += convertNanosecondsToMilliseconds(endTime - startTime);
            deltaSum += delta;
        }

        double averageExecutingTime = executingTimeSum / simulations;
        double averageDelta = deltaSum / simulations;

        return new SimulationResult(averageExecutingTime, averageDelta);
    }

    private double convertNanosecondsToMilliseconds(double nanoseconds) {
        return nanoseconds / 1_000_000d;
    }

}
