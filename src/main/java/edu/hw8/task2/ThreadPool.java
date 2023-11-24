package edu.hw8.task2;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);

    static MyThreadPool newFixedTheadPool(int nThread) {
        return new MyThreadPool(nThread);
    }
}
