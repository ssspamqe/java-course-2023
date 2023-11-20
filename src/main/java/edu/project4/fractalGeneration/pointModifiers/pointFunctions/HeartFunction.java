package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.point.Point;

public class HeartFunction implements PointFunction {

    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.getX();
        double y = oldPoint.getY();

        double sqrt = Math.sqrt(x * x + y * y);
        double trigonomArg = sqrt * Math.atan(y / x);

        double newX = sqrt * Math.sin(trigonomArg);
        double newY = -sqrt * Math.cos(trigonomArg);

        return new Point(newX, newY);
    }
}
