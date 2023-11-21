package edu.project4.fractalGeneration;

import edu.project4.fractalGeneration.graphics.Pixel;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.coordinateObjects.Point;
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

    public static PixelCanvas create(
        int samples,
        int iterationsPerSample,
        int offset,
        int height,
        int width,
        boolean verticalSymmetry,
        boolean horizontalSymmetry,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions
    ) {

        PixelCanvas canvas = new PixelCanvas(height, width, verticalSymmetry, horizontalSymmetry);

        for (int sample = 0; sample < samples; sample++) {
            Point newPoint = getRandomInitialPoint();

            for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
                AffineTransformation transformation = getRandomTransformation(transformations);

                Point transformedPoint = newPoint.getTransformedPoint(transformation);
                newPoint = applyPointFunctions(transformedPoint, pointFunctions);

                if (iteration >= 0
                    && (newPoint.getX() >= X_MIN && newPoint.getX() <= X_MAX)
                    && (newPoint.getY() >= Y_MIN && newPoint.getY() <= Y_MAX)) {

                    Dot dot = getDot(newPoint, height, width);

                    if (dot.x() < height && dot.y() < width) {
                        paintPixel(dot,canvas,transformation.getColor());
                    }
                }
            }
        }

        return canvas;
    }

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

    private static Dot getDot(Point point, int height, int width) {
        int x = height - (int) (((X_MAX - point.getX()) / (X_MAX - X_MIN)) * height);
        int y = width - (int) (((Y_MAX - point.getY()) / (Y_MAX - Y_MIN)) * width);

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
