package edu.hw1;

public class Task2 {
    @SuppressWarnings("MagicNumber")
    public int countDigits(int num) {

        int absNum = Math.abs(num);

        int res = 1;

        while (absNum >= 10) {
            res++;
            absNum /= 10;
        }
        return res;
    }
}
