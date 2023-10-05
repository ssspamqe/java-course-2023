package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task6 {
    @SuppressWarnings("MagicNumber")
    private int getNum(List<Integer> list) {
        int res = 0;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            res += (int) (list.get(i) * Math.pow(10, i));
        }

        return res;

    }

    @SuppressWarnings("MagicNumber")
    private int getKStep(int num) {

        int localNum = num;

        List<Integer> digits = new ArrayList<>();
        while (localNum > 10) {
            digits.add(localNum % 10);
            localNum /= 10;
        }
        digits.add(localNum);

        Collections.sort(digits);
        int descending = getNum(digits);

        Collections.reverse(digits);
        int ascending = getNum(digits);

        return descending - ascending;
    }

    @SuppressWarnings("MagicNumber")
    public int countK(int num) {

        if (!(num > 1000 && num <= 9999)) {
            throw new IllegalArgumentException("num expected to be in range (1000,9999]");
        }

        int localNum = num;

        int steps = 0;
        while (localNum != 6174) {
            localNum = getKStep(localNum);
            steps++;
        }
        return steps;
    }
}
