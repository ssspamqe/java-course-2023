package edu.project4;

import edu.project4.fractalGeneration.fractalCreators.MultiThreadFractalCreator;
import edu.project4.fractalGeneration.fractalCreators.SingleThreadFractalCreator;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PolarFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SinusoidalFunction;
import edu.project4.fractalGeneration.postProcessing.GammaCorrection;
import edu.project4.fractalGeneration.postProcessing.PostProcessing;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

    private static final int SAMPLES = 1_000_000;
    private static final int ITERATIONS_PER_SAMPLE = 100;
    private static final int OFFSET = -20;

    private static final int HEIGHT = 2000;
    private static final int WIDTH = 2000;

    private static final boolean VERTICAL_SYMMETRY = false;
    private static final boolean HORIZONTAL_SYMMETRY = false;

    public static void main(String[] params) {
        List<AffineTransformation> transformations = getListOfAffineTransformations(70);
        List<PointFunction> pointFunctions =
            List.of(
                new SinusoidalFunction(),
                new PolarFunction()
            );

        double singleThreadTime = 0;
        for (int i = 0; i < 5; i++) {
            var start = System.nanoTime();
            SingleThreadFractalCreator.create(
                SAMPLES,
                ITERATIONS_PER_SAMPLE,
                OFFSET,
                HEIGHT,
                WIDTH,
                VERTICAL_SYMMETRY,
                HORIZONTAL_SYMMETRY,
                transformations,
                pointFunctions
            );
            var end = System.nanoTime();
            System.out.println(end - start);
            singleThreadTime += end - start;
        }
        singleThreadTime /= 5_000_000_000L;
        System.out.println(singleThreadTime);


        for (int threads = 2; threads <= 10; threads++) {
            double allTime = 0;

            for (int i = 0; i < 5; i++) {
                var start = System.nanoTime();
                MultiThreadFractalCreator.create(
                    SAMPLES,
                    ITERATIONS_PER_SAMPLE,
                    OFFSET,
                    HEIGHT,
                    WIDTH,
                    VERTICAL_SYMMETRY,
                    HORIZONTAL_SYMMETRY,
                    transformations,
                    pointFunctions,
                    threads
                );
                var end = System.nanoTime();
                allTime+=end-start;
            }
            allTime/=5_000_000_000L;

            System.out.println(threads + " - " +singleThreadTime/allTime);
        }

//        PostProcessing gammaCorrection = new GammaCorrection();
//        gammaCorrection.applyProcedure(canvas);
//
//        ImageRenderer.renderImage(canvas);
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

            double c = ThreadLocalRandom.current().nextDouble(-10, 10);
            double f = ThreadLocalRandom.current().nextDouble(-10, 10);

            int red = ThreadLocalRandom.current().nextInt(0, 256);
            int green = ThreadLocalRandom.current().nextInt(0, 256);
            int blue = ThreadLocalRandom.current().nextInt(0, 256);

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

}
