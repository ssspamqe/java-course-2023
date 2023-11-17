package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PiCalculator {

    private  final Logger LOGGER = LogManager.getLogger();
    private  final Random RANDOM = new Random();
    private  final double RADIUS = 1;
    private  final Point CENTER = new Point(RADIUS, RADIUS);
    private  final int DEFAULT_POINTS_AMOUNT = 500;

    public double getPi(int pointsAmount) {
        int circlePoints = getRandomPointsInCircleAmount(pointsAmount);
        return calculatePi(circlePoints, pointsAmount);
    }

    public double getPi() {
        return getPi(DEFAULT_POINTS_AMOUNT);
    }

    public double getPiAsync(int threadsAmount, int pointsPerThread) {
        List<Thread> threads = new ArrayList<>();
        AtomicInteger pointsInCircle = new AtomicInteger();

        for (int i = 0; i < threadsAmount; i++) {
            threads.add(
                new Thread(() ->
                    pointsInCircle.addAndGet(getRandomPointsInCircleAmount(pointsPerThread))
                )
            );
            LOGGER.info("launcher thread");
            threads.getLast().start();
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

        return calculatePi(pointsInCircle.get(), pointsPerThread * threadsAmount);
    }

    public double getPiAsync(int threadAmount) {
        return getPiAsync(DEFAULT_POINTS_AMOUNT / threadAmount, threadAmount);
    }

    private int getRandomPointsInCircleAmount(int pointsAmount) {
        int pointsInCircle = 0;

        for (int i = 0; i < pointsAmount; i++) {
            Point randomPoint = getRandomPoint();
            if (liesInCircle(CENTER, randomPoint)) {
                pointsInCircle++;
            }
        }
        LOGGER.info("returning points...");
        return pointsInCircle;
    }

    private Point getRandomPoint() {
        return new Point(
            RANDOM.nextDouble(0, 2 * RADIUS),
            RANDOM.nextDouble(0, 2 * RADIUS)
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

    private double calculatePi(int pointsInCircle, int allPoints) {
        return 4 * ((double) pointsInCircle / allPoints);
    }

    private record Point(double x, double y) {
    }

}
