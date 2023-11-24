package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.coordinateObjects.Point;

public class PolarFunction implements PointFunction {
    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = Math.atan(y / x) / Math.PI;
        double newY = Math.sqrt(x * x + y * y) - 1;

        return new Point(newX, newY);
    }
}
