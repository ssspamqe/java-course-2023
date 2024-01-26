package edu.project4.fractalGeneration.postProcessing;

import edu.project4.fractalGeneration.graphics.Pixel;
import edu.project4.fractalGeneration.graphics.PixelCanvas;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class GammaCorrection implements PostProcessing {

    private static final double GAMMA = 3;

    @Override
    public void applyProcedure(PixelCanvas canvas) {
        setPixelNormal(canvas);
        double maxGamma = getMaxGamma(canvas);
        rePaintCanvas(canvas, maxGamma);
    }

    private void rePaintCanvas(PixelCanvas canvas, double maxGamma) {
        Set<Pixel> correctedPixels = new HashSet<>();
        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int column = 0; column < canvas.getWidth(); column++) {
                var pixel = canvas.getPixel(row, column);
                if (!correctedPixels.contains(pixel)) {
                    rePaintPixel(pixel, maxGamma);
                }
                correctedPixels.add(pixel);
            }
        }
    }

    private void rePaintPixel(Pixel pixel, double maxGamma) {
        pixel.setNormal(pixel.getNormal() / maxGamma);

        double coefficient = Math.pow(pixel.getNormal(), (1 / GAMMA));

        int red = (int) (pixel.getColor().getRed() * coefficient);
        int green = (int) (pixel.getColor().getGreen() * coefficient);
        int blue = (int) (pixel.getColor().getBlue() * coefficient);

        Color newColor = new Color(red, green, blue);

        pixel.setColor(newColor);
    }

    private void setPixelNormal(PixelCanvas canvas) {
        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int col = 0; col < canvas.getWidth(); col++) {
                var pixel = canvas.getPixel(row, col);
                if (pixel.getHits() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHits()));
                }
            }
        }
    }

    private double getMaxGamma(PixelCanvas canvas) {
        double maxGamma = Double.MIN_VALUE;

        for (int row = 0; row < canvas.getHeight(); row++) {
            for (int col = 0; col < canvas.getWidth(); col++) {
                var pixel = canvas.getPixel(row, col);
                if (pixel.getHits() != 0) {
                    maxGamma = Math.max(maxGamma, pixel.getNormal());
                }
            }
        }

        return maxGamma;
    }

}
