package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.point.DoublePoint;

public class SinusoidalFunction implements PointFunction{
    @Override
    public DoublePoint get(DoublePoint oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = Math.sin(x);
        double newY = Math.sin(y);

        return new DoublePoint(newX, newY);
    }
}
