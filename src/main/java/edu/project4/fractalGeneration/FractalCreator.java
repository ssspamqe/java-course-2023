package edu.project4.fractalGeneration;

import edu.project4.Pixel;
import edu.project4.fractalGeneration.point.Dot;
import edu.project4.fractalGeneration.point.Point;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FractalCreator {

    private static final int X_MIN = -1;
    private static final int X_MAX = 1;
    private static final int Y_MIN = -1;
    private static final int Y_MAX = 1;

    public static void create(
        int samples,
        int iterationsPerSample,
        int offset,
        int xRes,
        int yRes,
        boolean horizontalSymmetry,
        boolean verticalSymmetry,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions
    ) {

        PixelCanvas canvas = new PixelCanvas(xRes, yRes, verticalSymmetry, horizontalSymmetry);

        for (int sample = 0; sample < samples; sample++) {
            Point newPoint = getRandomInitialPoint();

            for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
                AffineTransformation transformation = getRandomTransformation(transformations);

                Point transformedPoint = newPoint.getTransformedPoint(transformation);
                newPoint = applyPointFunctions(transformedPoint, pointFunctions);

                if (iteration >= 0
                    && (newPoint.getX() >= X_MIN && newPoint.getX() <= X_MAX)
                    && (newPoint.getY() >= Y_MIN && newPoint.getY() <= Y_MAX)) {

                    Dot dot = getDot(newPoint, xRes, yRes);

                    if (dot.x() < xRes && dot.y() < yRes) {
                        paintPixel(dot,canvas,transformation.getColor());
                    }
                }
            }
        }
    }

//    void correction(int xRes, int yRes) {
//        double max = 0;
//        double gamma = 2.2;
//        for (int row = 0; row < xRes; row++) {
//            for (int col = 0; col < yRes; col++) {
//                var pixel = pixels.get(row).get(col);
//                if (pixel.getHits() != 0) {
//                    pixel.normal = Math.log10(pixel.getHits());
//                    max = Math.max(max, pixel.normal);
//                }
//            }
//        }
//
//        for (int row = 0; row < xRes; row++) {
//            for (int col = 0; col < yRes; col++) {
//                var pixel = pixels.get(row).get(col);
//                pixel.normal /= max;
//                double coefficient = Math.pow(pixel.normal, (1 / gamma));
//
//                pixel.red = (int) (pixel.red * coefficient);
//                pixel.green = (int) (pixel.green * coefficient);
//                pixel.blue = (int) (pixel.blue * coefficient);
//            }
//        }
//    }

//    private void fillTransformations(int amount) {
//        for (int i = 0; i < amount; i++) {
//            transformations.add(generateAffineTransformation());
//        }
//    }

//    private AffineTransformation generateAffineTransformation() {
//        while (true) {
//            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
//            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
//            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
//            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
//
//            double c = ThreadLocalRandom.current().nextDouble(-10, 10);
//            double f = ThreadLocalRandom.current().nextDouble(-10, 10);
//
//            if (areCorrectCoefficients(a, b, d, e)) {
//                return new AffineTransformation(a, b, c, d, e, f);
//            }
//        }
//
//    }
//
//    private boolean areCorrectCoefficients(double a, double b, double d, double e) {
//        return a * a + d * d < 1
//            && b * b + e * e < 1
//            && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
//    }

    private static Point getRandomInitialPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX),
            ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX)
        );
    }

    private static Point applyPointFunctions(Point point, List<PointFunction> functions) {
        Point resPoint = functions.get(0).apply(point);
        for (int i = 1; i < functions.size(); i++) {
            resPoint = functions.get(i).apply(resPoint);
        }
        return resPoint;
    }

    private static Dot getDot(Point point, int xRes, int yRes) {
        int x = xRes - (int) (((X_MAX - point.getX()) / (X_MAX - X_MIN)) * xRes);
        int y = yRes - (int) (((Y_MAX - point.getY()) / (Y_MAX - Y_MIN)) * yRes);

        return new Dot(x, y);
    }

    private static void paintPixel(Dot dot, PixelCanvas canvas, Color transformationColor) {
        Pixel pixel = canvas.getPixel(dot);

        if (pixel.getHits() == 0) {
            pixel.setColor(transformationColor);
        } else {
            pixel.mixColor(transformationColor);
        }
        pixel.incrementHits();
    }

    private static AffineTransformation getRandomTransformation(List<AffineTransformation> transformations){
        return transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
    }

}
