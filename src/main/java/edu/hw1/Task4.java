package edu.hw1;

public class Task4 {
    public String fixString(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i += 2) {
            if (i + 1 < n) {
                res.append(s.charAt(i + 1));
                res.append(s.charAt(i));
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
