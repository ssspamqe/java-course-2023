package edu.project4.fractalGeneration;

import edu.project4.Pixel;
import java.util.ArrayList;
import java.util.List;

public class PixelCanvas {

    private final int xSize;
    private final int ySize;

    private final boolean verticalSymmetry;
    private final boolean horizontalSymmetry;

    private List<List<Pixel>> canvas;

    public PixelCanvas(int xSize, int ySize, boolean verticalSymmetry, boolean horizontalSymmetry) {
        this.xSize = xSize;
        this.ySize = ySize;

        this.verticalSymmetry = verticalSymmetry;
        this.horizontalSymmetry = horizontalSymmetry;

        fillCanvas();
    }

    public PixelCanvas(int xSize, int ySize) {
        this(xSize, ySize, false, false);
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
        canvas = new ArrayList<>(xSize);

        for (int row = 0; row < xSize; row++) {
            List<Pixel> pixelRow = new ArrayList<>(ySize);
            for (int column = 0; column < ySize; column++) {
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
        int maxY = ySize;

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

        int maxX = xSize;
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
        canvas = new ArrayList<>(xSize);

        for (int row = 0; row < xSize; row++) {
            List<Pixel> pixelRow = new ArrayList<>(ySize);
            for (int column = 0; column < ySize; column++) {
                pixelRow.add(null);
            }
            canvas.add(pixelRow);
        }
    }

    private int getVerticallyMirroredDot(int y) {
        return ySize - y - 1;
    }

    private int getHorizontallyMirroredDot(int x) {
        return xSize - x - 1;
    }

    private int getMaxX() {
        return xSize / 2 + (1 - xSize % 2);
    }

    private int getMaxY() {
        return ySize / 2 + (1 - ySize % 2);
    }


    public Pixel getPixel(int x, int y){
        return canvas.get(x).get(y);
    }

}
