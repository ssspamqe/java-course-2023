package edu.hw1;

public class Task5 {
    private boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(int num) {
        return isPalindrome(String.valueOf(num));
    }

    public boolean isPalindromeDescendant(int num) {

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
}
