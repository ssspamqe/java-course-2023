package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.coordinateObjects.Point;

public class SphericalFunction implements PointFunction {

    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.getX();
        double y = oldPoint.getY();

        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);

        return new Point(newX, newY);
    }
}
