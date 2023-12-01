package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloPiCalculator {
    private static final double RADIUS = 1;
    private static final Point CENTER = new Point(RADIUS, RADIUS);
    private static final int DEFAULT_POINTS_AMOUNT = 500;

    public double getPiAsync(int threadsAmount, int pointsPerThread) {
        List<Thread> threads = new ArrayList<>();
        AtomicInteger pointsInCircle = new AtomicInteger();

        for (int i = 0; i < threadsAmount; i++) {
            threads.add(
                new Thread(() ->
                    pointsInCircle.addAndGet(getRandomAmountOfPointsInCircle(pointsPerThread))
                )
            );
            threads.getLast().start();
        }

        threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        );

        return calculatePi(pointsInCircle.get(), pointsPerThread * threadsAmount);
    }

    public double getPiAsync(int threadAmount) {
        return getPiAsync(DEFAULT_POINTS_AMOUNT / threadAmount, threadAmount);
    }

    private int getRandomAmountOfPointsInCircle(int pointsAmount) {
        int pointsInCircle = 0;

        for (int i = 0; i < pointsAmount; i++) {
            Point randomPoint = getRandomPoint();
            if (liesInCircle(CENTER, randomPoint)) {
                pointsInCircle++;
            }
        }
        return pointsInCircle;
    }

    private Point getRandomPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(0, 2 * RADIUS),
            ThreadLocalRandom.current().nextDouble(0, 2 * RADIUS)
        );
    }

    private boolean liesInCircle(Point center, Point point) {
        return getDistance(center, point) <= RADIUS;
    }

    private double getDistance(Point a, Point b) {
        return Math.sqrt(
            Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)
        );
    }

    @SuppressWarnings("MagicNumber")
    private double calculatePi(int pointsInCircle, int allPoints) {
        return 4 * ((double) pointsInCircle / allPoints);
    }

    private record Point(double x, double y) {
    }

}
