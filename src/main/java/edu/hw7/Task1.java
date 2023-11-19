package edu.hw7;

import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public static int incrementAsync(int num, @Positive int threads) {
        var res = new AtomicInteger(num);
        List<Thread> launcherThread = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            launcherThread.add(new Thread(res::incrementAndGet));
            launcherThread.getLast().start();
        }

        launcherThread.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return res.get();
    }
}
