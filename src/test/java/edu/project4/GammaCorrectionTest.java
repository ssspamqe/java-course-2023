package edu.project4;

import edu.project4.fractalGeneration.coordinateObjects.Dot;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.fractalGeneration.postProcessing.GammaCorrection;
import java.awt.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GammaCorrectionTest {

    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;

    private final GammaCorrection correction = new GammaCorrection();

    @Test
    @DisplayName("Gamma correction should repaint hitted pixels")
    void gammaCorrection_should_repaintPixels() {
        PixelCanvas canvas = new PixelCanvas(HEIGHT, WIDTH);
        Dot dot1 = new Dot(0, 0);
        Dot dot2 = new Dot(0, 1);
        Color defaultColor = canvas.getPixel(dot1).getColor();

        canvas.getPixel(dot1).setHits(100);
        canvas.getPixel(dot2).setHits(50);
        canvas.getPixel(dot2).setColor(new Color(100, 100, 100));
        correction.applyProcedure(canvas);
        Color dot2Color = canvas.getPixel(dot2).getColor();

        assertThat(dot2Color).isNotEqualTo(defaultColor);
    }

}
