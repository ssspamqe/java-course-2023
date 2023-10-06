package edu.hw1;

public class Task7 {
    public int rotateLeft(int n, int shift) {


        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        if (shift < 0) {
            return rotateRight(n, Math.abs(shift));
        }

        int size = (int) (Math.log(n) / Math.log(2)) + 1;
        int localShift = shift % size;

        int leftPart = n >> (size - localShift);

        int rightPart = n - (int) Math.pow(2, size - localShift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, localShift);
    }

    public int rotateRight(int n, int shift) {

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        if (shift < 0) {
            return rotateLeft(n, Math.abs(shift));
        }

        int size = (int) (Math.log(n) / Math.log(2)) + 1;
        int localShift = shift % size;

        int leftPart = n >> localShift;

        int rightPart = n - (int) Math.pow(2, localShift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, size - localShift);
    }
}
