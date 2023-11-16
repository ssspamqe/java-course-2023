package edu.hw7.Task4;

public class Driver {
    public static void main(String[] params) {
        int randomPoints = 100000000;

        var start = System.nanoTime();
        DeltaCounter.getAverageDelta(
            1,
            Math.PI,
            () -> PiCalculator.getPi(randomPoints)
        );

        var end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        DeltaCounter.getAverageDelta(
            1,
            Math.PI,
            () -> PiCalculator.getPiAsync(2, randomPoints / 2)
        );

        end = System.nanoTime();
        System.out.println(end - start);
    }
}
