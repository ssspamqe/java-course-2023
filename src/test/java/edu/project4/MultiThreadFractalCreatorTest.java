package edu.project4;

import edu.project4.fractalGeneration.fractalCreators.MultiThreadFractalCreator;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.DiskFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.HeartFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PointFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PolarFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SinusoidalFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SphericalFunction;
import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadFractalCreatorTest {

    private static final int SAMPLES = 1_000_000;
    private static final int ITERATIONS_PER_SAMPLE = 100;
    private static final int OFFSET = -20;

    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;

    private static final int THREADS = 4;

    @Test
    @DisplayName("MultiThreadFractalCreator should fill given PixelCanvas")
    void fractalCreator_should_fillGivenPixelCanvas() {
        var canvas = new PixelCanvas(HEIGHT, WIDTH);
        List<PointFunction> pointFunctions =
            List.of(
                new SinusoidalFunction(),
                new DiskFunction(),
                new HeartFunction(),
                new PolarFunction(),
                new SphericalFunction()
            );

        double a = -0.57;
        double b = -0.5;
        double d = 0.15;
        double e = 0.08;
        double c = 0;
        double f = 0;
        var color = new Color(5, 17, 1);
        var transformations = List.of(new AffineTransformation(a, b, c, d, e, f, color));


        MultiThreadFractalCreator.fillCanvas(
            canvas,
            SAMPLES,
            ITERATIONS_PER_SAMPLE,
            OFFSET,
            transformations,
            pointFunctions,
            THREADS
        );

        int blackPixels = getAmountOfBlackPixels(canvas);


        assertThat(blackPixels).isNotEqualTo(HEIGHT * WIDTH);
    }

    private int getAmountOfBlackPixels(PixelCanvas canvas) {
        Color black = new Color(0, 0, 0);
        int count = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (canvas.getPixel(i, j).getColor().equals(black)) {
                    count++;
                }
            }
        }
        return count;
    }

}
