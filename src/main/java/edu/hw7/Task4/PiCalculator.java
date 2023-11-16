package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PiCalculator {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();
    private static final double RADIUS = 1;
    private static final Point CENTER = new Point(RADIUS, RADIUS);
    private static final int DEFAULT_POINTS_AMOUNT = 500;

    public static double getPi(int pointsAmount) {
        int circlePoints = getRandomPointsInCircleAmount(pointsAmount);
        return calculatePi(circlePoints, pointsAmount);
    }

    public static double getPi() {
        return getPi(DEFAULT_POINTS_AMOUNT);
    }

    public static double getPiAsync(int threadsAmount, int pointsPerThread) {
        List<Thread> threads = new ArrayList<>();
        List<Integer> pointsInCircle = new ArrayList<>();

        for (int i = 0; i < threadsAmount; i++) {
            threads.add(
                new Thread(() ->
                    pointsInCircle.add(getRandomPointsInCircleAmount(pointsPerThread))
                )
            );
            threads.get(i).start();
        }

        threads.forEach(thread -> {
                try {
                    var start = System.nanoTime();
                    thread.join();
                    var end = System.nanoTime();
                    LOGGER.info("waited thread for " + (end - start));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        );

        int allPointsInCircle = pointsInCircle.stream().reduce(Integer::sum).orElse(0);
        return calculatePi(allPointsInCircle, pointsPerThread * threadsAmount);
    }

    public static double getPiAsync(int threadAmount) {
        return getPiAsync(DEFAULT_POINTS_AMOUNT / threadAmount, threadAmount);
    }

    private static int getRandomPointsInCircleAmount(int pointsAmount) {
        int pointsInCircle = 0;

        for (int i = 0; i < pointsAmount; i++) {
            Point randomPoint = getRandomPoint();
            if (liesInCircle(CENTER, randomPoint)) {
                pointsInCircle++;
            }
        }

        return pointsInCircle;
    }

    private static Point getRandomPoint() {
        return new Point(
            RANDOM.nextDouble(0, 2 * RADIUS),
            RANDOM.nextDouble(0, 2 * RADIUS)
        );
    }

    private static boolean liesInCircle(Point center, Point point) {
        return getDistance(center, point) <= RADIUS;
    }

    private static double getDistance(Point a, Point b) {
        return Math.sqrt(
            Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)
        );
    }

    private static double calculatePi(int pointsInCircle, int allPoints) {
        return 4 * ((double) pointsInCircle / allPoints);
    }

    private static record Point(double x, double y) {
    }

}
