package edu.project4;

import edu.project4.fractalGeneration.fractalCreators.MultiThreadFractalCreator;
import edu.project4.fractalGeneration.fractalCreators.SingleThreadFractalCreator;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PolarFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SinusoidalFunction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int SAMPLES = 1_000_000;
    private static final int ITERATIONS_PER_SAMPLE = 100;
    private static final int OFFSET = -20;

    private static final int HEIGHT = 2000;
    private static final int WIDTH = 2000;

    private static final boolean VERTICAL_SYMMETRY = false;
    private static final boolean HORIZONTAL_SYMMETRY = false;

    private static final int MAX_COLOR = 255;

    private static final int MAX_ABSOLUTE_SHIFT = 10;

    private static final double NANO_IN_MILLI = 1_000_000;

    private static final int SIMULATIONS = 5;

    private static final int MIN_THREADS = 2;
    private static final int MAX_THREADS = 10;

    private static final int AMOUNT_OF_AFFINE_TRANSFORMATIONS = 70;

    private Driver() {
    }

    public static void launch() {
        List<AffineTransformation> transformations = getListOfAffineTransformations(AMOUNT_OF_AFFINE_TRANSFORMATIONS);
        List<PointFunction> pointFunctions =
            List.of(
                new SinusoidalFunction(),
                new PolarFunction()
            );

        double singleThreadTime = getSingleThreadTime(transformations, pointFunctions);
        LOGGER.info("Single thread time: {}", singleThreadTime);

        var multiThreadTimes = getMultiThreadTimes(transformations, pointFunctions);
        multiThreadTimes.forEach((key, value) -> LOGGER.info("{} - {}", key, value));
    }

    private static List<AffineTransformation> getListOfAffineTransformations(int n) {
        List<AffineTransformation> transformations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            transformations.add(generateAffineTransformation());
        }
        return transformations;
    }

    private static AffineTransformation generateAffineTransformation() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);

            double c = ThreadLocalRandom.current().nextDouble(-MAX_ABSOLUTE_SHIFT, MAX_ABSOLUTE_SHIFT);
            double f = ThreadLocalRandom.current().nextDouble(-MAX_ABSOLUTE_SHIFT, MAX_ABSOLUTE_SHIFT);

            int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
            int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
            int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);

            Color color = new Color(red, green, blue);

            if (areCorrectCoefficients(a, b, d, e)) {
                return new AffineTransformation(a, b, c, d, e, f, color);
            }
        }

    }

    private static boolean areCorrectCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }

    private static double getSingleThreadTime(
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions
    ) {
        double singleThreadTime = 0;
        PixelCanvas canvas = new PixelCanvas(HEIGHT, WIDTH, VERTICAL_SYMMETRY, HORIZONTAL_SYMMETRY);
        for (int i = 0; i < SIMULATIONS; i++) {
            var start = System.nanoTime();
            SingleThreadFractalCreator.fillCanvas(
                canvas,
                SAMPLES,
                ITERATIONS_PER_SAMPLE,
                OFFSET,
                transformations,
                pointFunctions
            );
            var end = System.nanoTime();
            singleThreadTime += end - start;
        }
        singleThreadTime /= NANO_IN_MILLI;
        singleThreadTime /= SIMULATIONS;

        return singleThreadTime;
    }

    private static Map<Integer, Double> getMultiThreadTimes(
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions
    ) {
        Map<Integer, Double> times = new HashMap<>();

        PixelCanvas canvas = new PixelCanvas(HEIGHT, WIDTH);

        for (int threads = MIN_THREADS; threads <= MAX_THREADS; threads++) {
            double allTime = 0;

            for (int i = 0; i < SIMULATIONS; i++) {
                var start = System.nanoTime();
                MultiThreadFractalCreator.fillCanvas(
                    canvas,
                    SAMPLES,
                    ITERATIONS_PER_SAMPLE,
                    OFFSET,
                    transformations,
                    pointFunctions,
                    threads
                );
                var end = System.nanoTime();
                allTime += end - start;
            }
            allTime /= NANO_IN_MILLI;
            allTime /= SIMULATIONS;

            times.put(threads, allTime);
        }

        return times;
    }

}
