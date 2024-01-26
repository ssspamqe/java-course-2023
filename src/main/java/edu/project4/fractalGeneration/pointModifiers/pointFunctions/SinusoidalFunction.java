package edu.project4.fractalGeneration.pointModifiers.pointFunctions;

import edu.project4.fractalGeneration.coordinateObjects.Point;

public class SinusoidalFunction implements PointFunction {
    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = Math.sin(x);
        double newY = Math.sin(y);

        return new Point(newX, newY);
    }
}
