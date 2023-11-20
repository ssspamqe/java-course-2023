package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.point.DoublePoint;

public class SphericalFunction implements PointFunction {

    @Override
    public DoublePoint get(DoublePoint oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);

        return new DoublePoint(newX, newY);
    }
}
