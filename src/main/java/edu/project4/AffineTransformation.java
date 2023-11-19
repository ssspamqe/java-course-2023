package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {

    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public final int red;
    public final int green;
    public final int blue;

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;

        red = ThreadLocalRandom.current().nextInt(0,255);
        green = ThreadLocalRandom.current().nextInt(0,255);
        blue = ThreadLocalRandom.current().nextInt(0,255);
    }
}
