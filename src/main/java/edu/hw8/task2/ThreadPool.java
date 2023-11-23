package edu.hw8.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface ThreadPool extends AutoCloseable {
    void start();
    void execute(Runnable runnable);
    static MyThreadPool newFixedTheadPool(int nThread){
        return new MyThreadPool(nThread);
    }
}
