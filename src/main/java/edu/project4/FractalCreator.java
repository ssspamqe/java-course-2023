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

    void create(int samples, int eqCount, int iterationsPerSample, int xRes, int yRes) {
        fillTransformations(eqCount);
        fillPixels(xRes, yRes);

        for (int num = 0; num < samples; num++) {
            double newX = ThreadLocalRandom.current().nextDouble(-1, 1);
            double newY = ThreadLocalRandom.current().nextDouble(-1, 1);

            for (int step =-20; step < iterationsPerSample; step++) {
                int i = ThreadLocalRandom.current().nextInt(0, eqCount);

                double x = transformations.get(i).a * newX + transformations.get(i).b * newY + transformations.get(i).c;
                double y = transformations.get(i).d * newX + transformations.get(i).e * newY + transformations.get(i).f;

                newX = Math.sin(x);
                newY = Math.sin(y);

                if (step >= 0 && (newX >= XMIN && newX <= XMAX) && (newY >= YMIN && newY <= YMAX)) {

                    int x1 = xRes - (int) (((XMAX - newX) / (XMAX - XMIN)) * xRes);
                    int y1 = yRes - (int) (((YMAX - newY) / (YMAX - YMIN)) * yRes);

                    if (x1 < xRes && y1 < yRes) {

                        Pixel pixel = pixels.get(x1).get(y1);

                        if (pixel.getHits() == 0) {
                            pixel.red = transformations.get(i).red;
                            pixel.green = transformations.get(i).green;
                            pixel.blue = transformations.get(i).blue;
                        } else {
                            pixel.red = (pixel.red + transformations.get(i).red) / 2;
                            pixel.green =
                                (pixel.green + transformations.get(i).green) / 2;
                            pixel.blue =
                                (pixel.blue + transformations.get(i).blue) / 2;
                        }

                        pixel.incrementHits();
                    }

                }

            }
        }

        correction(xRes,yRes);
    }


    void correction(int xRes, int yRes){
        double max = 0;
        double gamma = 2.2;
        for(int row = 0; row<xRes;row++){
            for(int col = 0; col < yRes;col++){
                var pixel = pixels.get(row).get(col);
                if(pixel.getHits()!=0){
                    pixel.normal = Math.log10(pixel.getHits());
                    max = Math.max(max, pixel.normal);
                }
            }
        }

        for(int row = 0; row <xRes;row++){
            for(int col =0; col < yRes;col++){
                var pixel = pixels.get(row).get(col);
                pixel.normal/=max;
                double coefficient = Math.pow(pixel.normal,(1/gamma));

                pixel.red = (int)(pixel.red * coefficient);
                pixel.green = (int)(pixel.green * coefficient);
                pixel.blue = (int)(pixel.blue * coefficient);
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

            double c = ThreadLocalRandom.current().nextDouble(-10, 10);
            double f = ThreadLocalRandom.current().nextDouble(-10, 10);

            if (areCorrectCoefficients(a, b, d, e)) {
                return new AffineTransformation(a, b, c, d, e, f);
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
