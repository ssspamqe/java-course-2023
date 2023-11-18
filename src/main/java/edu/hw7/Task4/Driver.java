package edu.hw7.Task4;

import edu.hw7.Task4.simulation.Simulator;

public class Driver {
    public static void main(String[] params) {
        var simulator = new Simulator();
        int simulations = 5;
        int randomPoints = 1_000_000_00;
        int threads = 2;
        int pointsPerThread = randomPoints / threads;

        System.out.println(
            simulator.getSingleThreadResult(
                simulations,
                Math.PI,
                randomPoints
            )
        );

        System.out.println(
            simulator.getMultiThreadResult(
                simulations,
                Math.PI,
                pointsPerThread,
                threads
            )
        );
    }
}
