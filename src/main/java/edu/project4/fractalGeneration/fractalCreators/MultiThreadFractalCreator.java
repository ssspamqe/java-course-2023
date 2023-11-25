package edu.project4.fractalGeneration.fractalCreators;

import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.coordinateObjects.Point;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadFractalCreator extends AbstractFractalCreator {

    private static final ReadWriteLock RW_LOCK = new ReentrantReadWriteLock();

    public static void fillCanvas(
        PixelCanvas canvas,
        int samples,
        int iterationsPerSample,
        int offset,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions,
        int threads
    ) {
        int samplesPerThread = samples / threads;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        executorService.execute(() ->
            procedureGeneration(
                samplesPerThread,
                iterationsPerSample,
                offset,
                transformations,
                pointFunctions,
                canvas
            )
        );

        executorService.close();
    }

    private static void procedureGeneration(
        int samples,
        int iterationsPerSample,
        int offset,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions,
        PixelCanvas canvas
    ) {
        for (int sample = 0; sample < samples; sample++) {
            Point startPoint = getRandomInitialPoint();
            iteratePoint(startPoint, offset, iterationsPerSample, transformations, pointFunctions, canvas);
        }
    }

    private static <T> T getRandomElementWithLock(List<T> list) {
        RW_LOCK.readLock().lock();
        T element = null;
        try {
            element = getRandomElement(list);
        } finally {
            RW_LOCK.readLock().unlock();
        }
        return element;
    }

    private static void paintPixelWithLock(Dot dot, PixelCanvas canvas, Color color) {
        RW_LOCK.writeLock().lock();
        try {
            paintPixel(dot, canvas, color);
        } finally {
            RW_LOCK.writeLock().unlock();
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
            AffineTransformation transformation = getRandomElementWithLock(transformations);

            Point transformedPoint = transformation.apply(newPoint);
            newPoint = applyPointFunctions(transformedPoint, pointFunctions);

            if (iteration >= 0
                && (newPoint.x() >= X_MIN && newPoint.x() <= X_MAX)
                && (newPoint.y() >= Y_MIN && newPoint.y() <= Y_MAX)) {

                Dot dot = getDot(newPoint, canvas.getHeight(), canvas.getWidth());

                if (dot.x() < canvas.getHeight() && dot.y() < canvas.getWidth()) {
                    paintPixelWithLock(dot, canvas, transformation.getColor());
                }
            }
        }
    }
}
