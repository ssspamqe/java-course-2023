package edu.project4.fractalGeneration.postProcessing;

import edu.project4.fractalGeneration.graphics.Pixel;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import java.awt.Color;

public class GammaCorrection implements PostProcessing {
    @Override
    public void applyProcedure(PixelCanvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        double maxGamma = 0;
        double gamma = 2.2;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                var pixel = canvas.getPixel(row, col);
                if (pixel.getHits() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHits()));
                    maxGamma = Math.max(maxGamma, pixel.getNormal());
                }
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                var pixel = canvas.getPixel(row, col);
                rePaintPixel(pixel, gamma, maxGamma);
            }
        }

    }

    private static void rePaintPixel(Pixel pixel, double gamma, double maxGamma) {
        pixel.setNormal(pixel.getNormal() / maxGamma);

        double coefficient = Math.pow(pixel.getNormal(), (1 / gamma));

        int red = (int) (pixel.getColor().getRed() * coefficient);
        int green = (int) (pixel.getColor().getGreen() * coefficient);
        int blue = (int) (pixel.getColor().getBlue() * coefficient);

        Color newColor = new Color(red, green, blue);

        pixel.setColor(newColor);
    }

}
