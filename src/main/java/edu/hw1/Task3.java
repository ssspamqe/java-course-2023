package edu.hw1;

public class Task3 {

    public boolean isNestable(int[] small, int[] big) {

        if (small.length == 0 || big.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int smallMin = Integer.MAX_VALUE;
        int smallMax = Integer.MIN_VALUE;

        for (var i : small) {
            smallMax = Integer.max(i, smallMax);
            smallMin = Integer.min(i, smallMin);
        }

        int bigMin = Integer.MAX_VALUE;
        int bigMax = Integer.MIN_VALUE;

        for (var i : big) {
            bigMax = Integer.max(i, bigMax);
            bigMin = Integer.min(i, bigMin);
        }

        if (smallMin > bigMin && smallMax < bigMax) {
            return true;
        }
        return false;
    }
}
