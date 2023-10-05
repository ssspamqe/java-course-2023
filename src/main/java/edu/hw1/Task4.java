package edu.hw1;

public class Task4 {
    public String fixString(String s) {
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
}
