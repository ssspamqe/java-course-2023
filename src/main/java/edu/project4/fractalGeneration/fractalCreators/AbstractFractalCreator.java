package edu.project4.fractalGeneration.fractalCreators;

import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.coordinateObjects.Point;
import edu.project4.fractalGeneration.graphics.Pixel;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractFractalCreator {

    protected static final int X_MIN = -1;
    protected static final int X_MAX = 1;
    protected static final int Y_MIN = -1;
    protected static final int Y_MAX = 1;

    protected static Point getRandomInitialPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX),
            ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX)
        );
    }

    protected static Point applyPointFunctions(Point point, List<PointFunction> functions) {
        Point resPoint = functions.get(0).apply(point);
        for (int i = 1; i < functions.size(); i++) {
            resPoint = functions.get(i).apply(resPoint);
        }
        return resPoint;
    }

    protected static Dot getDot(Point point, int height, int width) {
        int x = height - (int) (((X_MAX - point.getX()) / (X_MAX - X_MIN)) * height);
        int y = width - (int) (((Y_MAX - point.getY()) / (Y_MAX - Y_MIN)) * width);

        return new Dot(x, y);
    }

    protected static void paintPixel(Dot dot, PixelCanvas canvas, Color transformationColor) {
        Pixel pixel = canvas.getPixel(dot);

        if (pixel.getHits() == 0) {
            pixel.setColor(transformationColor);
        } else {
            pixel.mixColor(transformationColor);
        }
        pixel.incrementHits();
    }

    protected static <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
