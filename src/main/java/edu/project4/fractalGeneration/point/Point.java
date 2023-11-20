package edu.project4.fractalGeneration.point;

import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;

public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point getTransformedPoint(AffineTransformation transformation){
        double newX = transformation.getA() * x + transformation.getB() * y + transformation.getC();
        double newY = transformation.getD() * x + transformation.getE() * y + transformation.getF();

        return new Point(newX, newY);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
