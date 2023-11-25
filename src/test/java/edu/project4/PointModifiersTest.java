package edu.project4;

import edu.project4.fractalGeneration.coordinateObjects.Point;
import edu.project4.fractalGeneration.pointModifiers.AffineTransformation;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.DiskFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.HeartFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.PolarFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SinusoidalFunction;
import edu.project4.fractalGeneration.pointModifiers.pointFunctions.SphericalFunction;
import java.awt.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PointModifiersTest {

    Point point = new Point(1, 1);

    @Test
    @DisplayName("DiskFunction should calculate disk funtion")
    void diskFuntion_should_returnCalculatedDiskFuntion() {
        var fun = new DiskFunction();
        var correctPoint = new Point(-0.24097563321246931, -0.06656383551035391);

        var returnedPoint = fun.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

    @Test
    @DisplayName("HeartFunction should calculate heart funtion")
    void heartFuntion_should_returnCalculatedHeatFuntion() {
        var fun = new HeartFunction();
        var correctPoint = new Point(1.2671621313307992, -0.6279332232978174);

        var returnedPoint = fun.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

    @Test
    @DisplayName("PolarFunction should calculate polar funtion")
    void polarFuntion_should_returnCalculatedPolarFuntion() {
        var fun = new PolarFunction();
        var correctPoint = new Point(0.25, 0.41421356237309515);

        var returnedPoint = fun.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

    @Test
    @DisplayName("SinusoidalFunction should calculate sinusoidal funtion")
    void sinusoidalFuntion_should_returnCalculatedsinusoidalFuntion() {
        var fun = new SinusoidalFunction();
        var correctPoint = new Point(0.8414709848078965, 0.8414709848078965);

        var returnedPoint = fun.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

    @Test
    @DisplayName("SphericalFunction should calculate disk funtion")
    void sphericalFuntion_should_returnCalculatedSphericalFuntion() {
        var fun = new SphericalFunction();
        var correctPoint = new Point(0.5, 0.5);

        var returnedPoint = fun.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

    @Test
    @DisplayName("AffineTransformation should calculate affine transformation of point")
    void affineTransformation_should_calculateAffineTransformation() {
        double a = -0.57;
        double b = -0.5;
        double d = 0.15;
        double e = 0.08;
        double c = 0;
        double f = 0;
        var color = new Color(5, 17, 1);
        var transformation = new AffineTransformation(a, b, c, d, e, f, color);
        var correctPoint = new Point(-1.0699999999999998, 0.22999999999999998);

        var returnedPoint = transformation.apply(point);

        assertThat(returnedPoint).isEqualTo(correctPoint);
    }

}
