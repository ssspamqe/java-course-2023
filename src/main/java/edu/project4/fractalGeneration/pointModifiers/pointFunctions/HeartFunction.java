package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.point.DoublePoint;

public class HeartFunction implements PointFunction {

    @Override
    public DoublePoint get(DoublePoint oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double sqrt = Math.sqrt(x * x + y * y);
        double trigonomArg = sqrt * Math.atan(y / x);

        double newX = sqrt * Math.sin(trigonomArg);
        double newY = -sqrt * Math.cos(trigonomArg);

        return new DoublePoint(newX, newY);
    }
}
