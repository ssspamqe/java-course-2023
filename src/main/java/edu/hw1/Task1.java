package edu.hw1;

public class Task1 {
    @SuppressWarnings("MagicNumber")
    public int minutesToSeconds(String time) {
        String[] params = time.split(":");
        int seconds = Integer.parseInt(params[1]);
        int minutes = Integer.parseInt(params[0]);

        if (seconds >= 60 || seconds < 0) {
            throw new IllegalArgumentException("Seconds expected to be in range [0;60)");
        }

        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes expected to be non negative");
        }

        return minutes * 60 + seconds;
    }
}


