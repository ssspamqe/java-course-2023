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
        color = new Color(
            (color.getRed() + newColor.getRed()) / 2,
            (color.getGreen() + newColor.getGreen()) / 2,
            (color.getBlue() + newColor.getBlue()) / 2
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
