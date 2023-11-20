package edu.project4.fractalGeneration.pointModifiers;

import java.awt.Color;

public class AffineTransformation {

    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private Color color;

    public AffineTransformation(double a, double b, double c, double d, double e, double f, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.color = color;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }

    public Color getColor() {
        return color;
    }
}
