package edu.project4.fractalGeneration.fractalCreators;

import edu.project4.fractalGeneration.graphics.Pixel;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.coordinateObjects.Point;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadFractalCreator extends AbstractFractalCreator {



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
}
