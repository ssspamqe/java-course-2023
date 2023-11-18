package edu.hw7.Task4;

import edu.hw7.Task4.simulation.Simulator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final List<Integer> AMOUNTS_OF_POINTS =
        List.of(
            10_000,
            100_000,
            1_000_000,
            10_000_000
        );
    private static final int POINTS_DEFAULT = 10_000_000;

    private static final int SIMULATIONS = 5;
    private static final Simulator SIMULATOR = new Simulator();

    private static final int THREADS_MIN = 2;
    private static final int THREADS_MAX = 10;
    private static final int THREADS_DEFAULT = 2;

    public static void Launch() {
        Map<Integer, Double> averageDeltas = getAverageDeltas(AMOUNTS_OF_POINTS);
        Map<Integer, Double> averageSpeedUp = getAverageSpeedUp(POINTS_DEFAULT);

        printDeltaStats(averageDeltas);
        LOGGER.info("");
        printSpeedUpStats(averageSpeedUp);
    }

    private static Map<Integer, Double> getAverageDeltas(List<Integer> amountsOfPoints) {
        Map<Integer, Double> averageDeltas = new TreeMap<>();

        for (var points : amountsOfPoints) {

            int pointsPerThread = points / THREADS_DEFAULT;

            var simResult = SIMULATOR.getAsyncResult(
                SIMULATIONS,
                THREADS_DEFAULT,
                pointsPerThread
            );

            averageDeltas.put(points, simResult.averageDelta());
        }
        return averageDeltas;
    }

    private static Map<Integer, Double> getAverageSpeedUp(int points) {
        double singleThreadExecutionTime =
            SIMULATOR.getAsyncResult(
                SIMULATIONS,
                1,
                points
            ).averageExecutionTime();

        Map<Integer, Double> averageExecutingTimes = new TreeMap<>();
        for (int threads = THREADS_MIN; threads <= THREADS_MAX; threads++) {
            int pointsPerThread = points/threads;
            var executionTime =
                SIMULATOR.getAsyncResult(
                    SIMULATIONS,
                    threads,
                    pointsPerThread
                ).averageExecutionTime();
            var speedUp = singleThreadExecutionTime / executionTime;
            averageExecutingTimes.put(threads, speedUp);
        }

        return averageExecutingTimes;
    }

    private static void printDeltaStats(Map<Integer, Double> stats) {
        LOGGER.info("Average delta:");
        LOGGER.info("(points - delta)");
        stats.forEach((key, value) -> {
            LOGGER.info("{} - {}", key, value);
        });
    }

    private static void printSpeedUpStats(Map<Integer, Double> stats) {
        LOGGER.info("Average speed up:");
        LOGGER.info("(threads - speed up)");
        stats.forEach((key, value) -> {
            LOGGER.info("{} - {}", key, value);
        });
    }

}
