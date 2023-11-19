package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FractalCreator {

    private static final int XMIN = -1;
    private static final int XMAX = 1;
    private static final int YMIN = -1;
    private static final int YMAX = 1;

    List<AffineTransformation> transformations = new ArrayList<>();

    public List<List<Pixel>> pixels;

    void create(int n, int eqCount, int it, int xRes, int yRes) {
        fillTransformations(eqCount);
        fillPixels(xRes, yRes);

        for (int num = 0; num < n; num++) {
            double newX = ThreadLocalRandom.current().nextDouble(XMIN, XMAX);
            double newY = ThreadLocalRandom.current().nextDouble(YMIN, YMAX);

            for (int step = -20; step < it; step++) {
                int i = ThreadLocalRandom.current().nextInt(0, eqCount);

                double x = transformations.get(i).a * newX + transformations.get(i).b * newY + transformations.get(i).c;
                double y = transformations.get(i).d * newX + transformations.get(i).e * newY + transformations.get(i).f;

                newX = Math.sin(x);
                newY = Math.sin(y);

                if (step >= 0 && (newX >= XMIN && newX <= XMAX) && (newY >= YMIN && newY <= YMAX)) {

                    int x1 = xRes - (int) (((XMAX - newX) / (XMAX - XMIN)) * xRes);
                    int y1 = yRes - (int) (((YMAX - newY) / (YMAX - YMIN)) * yRes);

                    if (x1 < xRes && y1 < yRes) {
                        if (pixels.get(x1).get(y1).getHits() == 0) {
                            pixels.get(x1).get(y1).red = transformations.get(i).red;
                            pixels.get(x1).get(y1).green = transformations.get(i).green;
                            pixels.get(x1).get(y1).blue = transformations.get(i).blue;
                        } else {
                            pixels.get(x1).get(y1).red = (pixels.get(x1).get(y1).red + transformations.get(i).red)/2;
                            pixels.get(x1).get(y1).green = (pixels.get(x1).get(y1).green + transformations.get(i).green)/2;
                            pixels.get(x1).get(y1).blue = (pixels.get(x1).get(y1).blue + transformations.get(i).blue)/2;
                        }

                        pixels.get(x1).get(y1).incrementHits();
                    }

                }

            }
        }

    }

    private void fillTransformations(int amount) {
        for (int i = 0; i < amount; i++) {
            transformations.add(generateAffineTransformation());
        }
    }

    private AffineTransformation generateAffineTransformation() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);

            if (areCorrectCoefficients(a, b, d, e)) {
                return new AffineTransformation(a, b, 1, d, e, 1);
            }
        }

    }

    private boolean areCorrectCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }

    private void fillPixels(int xRes, int yRes) {
        pixels = new ArrayList<>();
        for (int i = 0; i < xRes; i++) {
            List<Pixel> line = new ArrayList<>();
            for (int j = 0; j < yRes; j++) {
                line.add(new Pixel(i, j));
            }
            pixels.add(line);
        }
    }

}
