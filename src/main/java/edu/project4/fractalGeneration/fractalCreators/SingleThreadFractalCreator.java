package edu.project4.fractalGeneration.fractalCreators;

import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.coordinateObjects.Point;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import java.util.List;

public class SingleThreadFractalCreator extends AbstractFractalCreator {

    public static void fillCanvas(
        PixelCanvas canvas,
        int samples,
        int iterationsPerSample,
        int offset,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions
    ) {
        for (int sample = 0; sample < samples; sample++) {
            Point startPoint = getRandomInitialPoint();
            iteratePoint(startPoint, offset,iterationsPerSample,transformations,pointFunctions,canvas);
        }
    }

    private static void iteratePoint(
        Point point,
        int offset,
        int iterationsPerSample,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions,
        PixelCanvas canvas
    ) {
        Point newPoint = point;
        for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
            AffineTransformation transformation = getRandomElement(transformations);

            Point transformedPoint = newPoint.getTransformedPoint(transformation);
            newPoint = applyPointFunctions(transformedPoint, pointFunctions);

            if (iteration >= 0
                && (newPoint.getX() >= X_MIN && newPoint.getX() <= X_MAX)
                && (newPoint.getY() >= Y_MIN && newPoint.getY() <= Y_MAX)) {

                Dot dot = getDot(newPoint, canvas.getHeight(), canvas.getWidth());

                if (dot.x() < canvas.getHeight() && dot.y() < canvas.getWidth()) {
                    paintPixel(dot, canvas, transformation.getColor());
                }
            }
        }
    }
}
