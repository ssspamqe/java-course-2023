package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Homework1 {
    private final static Logger LOGGER = LogManager.getLogger();

    static void logHelloWorld() {
        LOGGER.info("Привет, мир!");
    }

    static int minutesToSeconds(String time) {
        String[] params = time.split(":");
        int seconds = Integer.parseInt(params[1]);
        int minutes = Integer.parseInt(params[0]);

        if (seconds >= 60 || seconds < 0 || minutes < 0) {
            return -1;
        }

        return minutes * 60 + seconds;
    }

    static int countDigits(int num) {

        num = Math.abs(num);

        int res = 1;

        while (num >= 10) {
            res++;
            num /= 10;
        }
        return res;

    }

    static boolean isNestable(int[] small, int[] big) {

        if (small.length == 0 || big.length == 0) {
            return false;
        }

        //Collections.max()
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

    static String fixString(String s) {
        String res = "";
        int n = s.length();
        for (int i = 0; i < n; i += 2) {
            if (i + 1 < n) {
                res += s.charAt(i + 1);
                res += s.charAt(i);
            } else {
                res += s.charAt(i);
            }
        }
        return res;
    }

    private static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(int num) {
        return isPalindrome(String.valueOf(num));
    }

    static boolean isPalindromeDescendant(int num) {

        while (true) {

            if (isPalindrome(num)) {
                return true;
            }

            String stringNum = String.valueOf(num);

            int res = 0;
            int n = stringNum.length();
            if (n % 2 == 1) {
                return false;
            }

            for (int i = 0; i < n; i++) {
                if (i + 1 >= n) {
                    break;
                }
                res +=
                    Character.getNumericValue(stringNum.charAt(i)) + Character.getNumericValue(stringNum.charAt(i + 1));
            }
            num = res;
        }
    }

    private static int getNum(List<Integer> list) {
        int res = 0;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            res += (int) (list.get(i) * Math.pow(10, i));
        }

        return res;

    }

    private static int getKStep(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num > 10) {
            digits.add(num % 10);
            num /= 10;
        }
        digits.add(num);

        Collections.sort(digits);
        int descending = getNum(digits);

        Collections.reverse(digits);
        int ascending = getNum(digits);

        return descending - ascending;
    }

    static int countK(int num) {
        int steps = 0;
        while (num != 6174) {
            num = getKStep(num);
            steps++;
        }
        return steps;
    }

    static int rotateLeft(int n, int shift) {

        int size = (int) (Math.log(n) / Math.log(2))+1;
        shift %= size;

        int leftPart = n >> (size - shift);

        int rightPart = n - (int) Math.pow(2, size - shift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, shift);
    }

    static int rotateRight(int n, int shift) {
        //StringBuffer s = StringBuffer Integer.toBinaryString(n);
        int size = (int) (Math.log(n) / Math.log(2))+1;
        shift %= size;

        int leftPart = n >> shift;

        int rightPart = n - (int) Math.pow(2, shift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, size - shift);
    }

    static boolean knightBoardCapture(int[][] board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == 1) {
                    if (r >= 2) { //up
                        if (c >= 1 && board[r - 2][c - 1] == 1) {
                            return false;
                        }
                        if (c <= 6 && board[r - 2][c + 1] == 1) {
                            return false;
                        }
                    }

                    if (r >= 5) { //down
                        if (c >= 1 && board[r + 2][c - 1] == 1) {
                            return false;
                        }
                        if (c <= 6 && board[r + 2][c + 1] == 1) {
                            return false;
                        }
                    }

                    if (c >= 2) { //left
                        if (r >= 1 && board[r - 1][c - 2] == 1) {
                            return false;
                        }
                        if (r <= 6 && board[r + 1][c - 2] == 1) {
                            return false;
                        }
                    }

                    if (c <= 5) {//right
                        if (r >= 1 && board[r - 1][c + 2] == 1) {
                            return false;
                        }
                        if (r <= 6 && board[r + 1][c + 2] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Homework1.rotateRight(35, 2);
    }
}
