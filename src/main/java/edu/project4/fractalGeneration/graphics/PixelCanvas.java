package edu.project4.fractalGeneration.graphics;

import edu.project4.fractalGeneration.coordinateObjects.Dot;
import java.util.ArrayList;
import java.util.List;

public class PixelCanvas {

    private final int height;
    private final int width;

    private final boolean verticalSymmetry;
    private final boolean horizontalSymmetry;

    private List<List<Pixel>> canvas;

    public PixelCanvas(int height, int width, boolean verticalSymmetry, boolean horizontalSymmetry) {
        this.height = height;
        this.width = width;

        this.verticalSymmetry = verticalSymmetry;
        this.horizontalSymmetry = horizontalSymmetry;

        fillCanvas();
    }

    public PixelCanvas(int height, int width) {
        this(height, width, false, false);
    }

    private void fillCanvas() {
        if (verticalSymmetry && horizontalSymmetry) {
            fillCanvasBothSymmetry();
        } else if (verticalSymmetry) {
            fillCanvasVerticalSymmetry();
        } else if (horizontalSymmetry) {
            fillCanvasHorizontalSymmetry();
        } else {
            fillCanvasNoSymmetry();
        }
    }

    private void fillCanvasNoSymmetry() {
        canvas = new ArrayList<>(height);

        for (int row = 0; row < height; row++) {
            List<Pixel> pixelRow = new ArrayList<>(width);
            for (int column = 0; column < width; column++) {
                pixelRow.add(new Pixel());
            }
            canvas.add(pixelRow);
        }
    }

    private void fillCanvasBothSymmetry() {
        fillNullCanvas();

        int maxX = getMaxX();
        int maxY = getMaxY();

        for (int row = 0; row < maxX; row++) {
            for (int column = 0; column < maxY; column++) {
                var pixel = new Pixel();

                canvas.get(row).set(column, pixel);
                canvas.get(row).set(getVerticallyMirroredDot(column), pixel);
                canvas.get(getHorizontallyMirroredDot(row)).set(column, pixel);
                canvas.get(getHorizontallyMirroredDot(row)).set(getVerticallyMirroredDot(column), pixel);
            }
        }
    }

    private void fillCanvasHorizontalSymmetry() {
        fillNullCanvas();

        int maxX = getMaxX();
        int maxY = width;

        for (int row = 0; row < maxX; row++) {
            for (int column = 0; column < maxY; column++) {
                var pixel = new Pixel();
                canvas.get(row).set(column, pixel);
                canvas.get(getHorizontallyMirroredDot(row)).set(column, pixel);
            }
        }

    }

    private void fillCanvasVerticalSymmetry() {
        fillNullCanvas();

        int maxX = height;
        int maxY = getMaxY();

        for (int row = 0; row < maxX; row++) {
            for (int column = 0; column < maxY; column++) {
                var pixel = new Pixel();
                canvas.get(row).set(column, pixel);
                canvas.get(row).set(getVerticallyMirroredDot(column), pixel);
            }
        }

    }

    private void fillNullCanvas() {
        canvas = new ArrayList<>(height);

        for (int row = 0; row < height; row++) {
            List<Pixel> pixelRow = new ArrayList<>(width);
            for (int column = 0; column < width; column++) {
                pixelRow.add(null);
            }
            canvas.add(pixelRow);
        }
    }

    private int getVerticallyMirroredDot(int y) {
        return width - y - 1;
    }

    private int getHorizontallyMirroredDot(int x) {
        return height - x - 1;
    }

    private int getMaxX() {
        return height / 2 + (1 - height % 2);
    }

    private int getMaxY() {
        return width / 2 + (1 - width % 2);
    }

    public Pixel getPixel(int x, int y) {
        return canvas.get(x).get(y);
    }

    public Pixel getPixel(Dot dot) {
        return getPixel(dot.x(), dot.y());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
