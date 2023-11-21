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

    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public static PixelCanvas create(
        int samples,
        int iterationsPerSample,
        int offset,
        int height,
        int width,
        boolean verticalSymmetry,
        boolean horizontalSymmetry,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions,
        int threads
    ) {
        PixelCanvas canvas = new PixelCanvas(height, width, verticalSymmetry, horizontalSymmetry);
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

        return canvas;
    }

    private static void procedureGeneration(
        int samples,
        int iterationsPerSample,
        int offset,
        List<AffineTransformation> transformations,
        List<PointFunction> pointFunctions,
        PixelCanvas canvas
    ) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        for (int sample = 0; sample < samples; sample++) {
            Point newPoint = getRandomInitialPoint();

            for (int iteration = offset; iteration < iterationsPerSample; iteration++) {
                AffineTransformation transformation = getRandomTransformationWithLock(transformations);

                Point transformedPoint = newPoint.getTransformedPoint(transformation);
                newPoint = applyPointFunctions(transformedPoint, pointFunctions);

                if (iteration >= 0
                    && (newPoint.getX() >= X_MIN && newPoint.getX() <= X_MAX)
                    && (newPoint.getY() >= Y_MIN && newPoint.getY() <= Y_MAX)) {

                    Dot dot = getDot(newPoint, height, width);

                    if (dot.x() < height && dot.y() < width) {
                        paintPixelWithLock(dot,canvas,transformation.getColor());
                    }
                }
            }
        }
    }

    private static AffineTransformation getRandomTransformationWithLock(List<AffineTransformation> transformations){
        rwLock.readLock().lock();
        AffineTransformation transformation = null;
        try{
            transformation = getRandomTransformation(transformations);
        } finally {
            rwLock.readLock().unlock();
        }
        return transformation;
    }

    private static void paintPixelWithLock(Dot dot, PixelCanvas canvas, Color color){
        rwLock.writeLock().lock();
        try{
            paintPixel(dot,canvas,color);
        } finally{
            rwLock.writeLock().unlock();
        }
    }

}
