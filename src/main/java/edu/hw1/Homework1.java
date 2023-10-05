package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Homework1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Homework1() {
    }

    static void logHelloWorld() {
        LOGGER.info("Привет, мир!");
    }

    @SuppressWarnings("MagicNumber")
    static int minutesToSeconds(String time) {
        String[] params = time.split(":");
        int seconds = Integer.parseInt(params[1]);
        int minutes = Integer.parseInt(params[0]);

        if (seconds >= 60 || seconds < 0 || minutes < 0) {
            return -1;
        }

        return minutes * 60 + seconds;
    }

    @SuppressWarnings("MagicNumber")
    static int countDigits(int num) {

        int absNum = Math.abs(num);

        int res = 1;

        while (absNum >= 10) {
            res++;
            absNum /= 10;
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

        int localNum = num;

        while (true) {

            if (isPalindrome(localNum)) {
                return true;
            }

            String stringNum = String.valueOf(localNum);

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
            localNum = res;
        }
    }

    @SuppressWarnings("MagicNumber")
    private static int getNum(List<Integer> list) {
        int res = 0;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            res += (int) (list.get(i) * Math.pow(10, i));
        }

        return res;

    }

    @SuppressWarnings("MagicNumber")
    private static int getKStep(int num) {

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
    static int countK(int num) {

        int localNum = num;

        int steps = 0;
        while (localNum != 6174) {
            localNum = getKStep(localNum);
            steps++;
        }
        return steps;
    }

    static int rotateLeft(int n, int shift) {

        int size = (int) (Math.log(n) / Math.log(2)) + 1;
        int localShift = shift % size;

        int leftPart = n >> (size - localShift);

        int rightPart = n - (int) Math.pow(2, size - localShift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, localShift);
    }

    static int rotateRight(int n, int shift) {
        //StringBuffer s = StringBuffer Integer.toBinaryString(n);
        int size = (int) (Math.log(n) / Math.log(2)) + 1;
        int localShift = shift % size;

        int leftPart = n >> localShift;

        int rightPart = n - (int) Math.pow(2, localShift) * leftPart;

        return leftPart + rightPart * (int) Math.pow(2, size - localShift);
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canBeatUp(int[][] board, int r, int c) {
        return r >= 2 && ((c >= 1 && board[r - 2][c - 1] == 1) || (c <= 6 && board[r - 2][c + 1] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canBeatDown(int[][] board, int r, int c) {
        return r <= 5 && ((c >= 1 && board[r + 2][c - 1] == 1) || (c <= 6 && board[r + 2][c + 1] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canBeatLeft(int[][] board, int r, int c) {
        return c >= 2 && ((r >= 1 && board[r - 1][c - 2] == 1) || (r <= 6 && board[r + 1][c - 2] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canBeatRight(int[][] board, int r, int c) {
        return c <= 5 && ((r >= 1 && board[r - 1][c + 2] == 1) || (r <= 6 && board[r + 1][c + 2] == 1));
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canBeat(int[][] board, int r, int c) {
        return canBeatRight(board, r, c)
            || canBeatLeft(board, r, c)
            || canBeatUp(board, r, c)
            || canBeatDown(board, r, c);
    }

    @SuppressWarnings("MagicNumber")
    static boolean knightBoardCapture(int[][] board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == 1) {
                    if (canBeat(board, r, c)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

//    public static void main(String[] args) {
//    }
}
