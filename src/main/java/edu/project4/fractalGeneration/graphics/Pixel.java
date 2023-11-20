package edu.project4.fractalGeneration.graphics;

import java.awt.Color;

public class Pixel {
    private Color color = Color.BLACK;
    private int hits = 0;
    private double normal = 0;

    public void incrementHits() {
        hits++;
    }

    public void mixColor(Color newColor) {
        int oldRed = color.getRed();
        int oldGreen = color.getGreen();
        int oldBlue = color.getBlue();

        color = new Color(
            (oldRed + newColor.getRed()) / 2,
            (oldGreen + newColor.getGreen()) / 2,
            (oldBlue + newColor.getBlue()) / 2
        );
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public int getHits() {
        return hits;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public double getNormal() {
        return normal;
    }

}
