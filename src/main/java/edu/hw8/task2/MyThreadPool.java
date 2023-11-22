package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool implements ThreadPool {

    private final int poolSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;

    public MyThreadPool(int poolSize) {
        this.poolSize = poolSize;
        taskQueue = new LinkedBlockingQueue<>();

        workers = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            workers.add(new Worker());
            workers.getLast().start();
        }
    }

    @Override
    public void start() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void execute(Runnable newTask) {
        taskQueue.add(newTask);
    }

    @Override
    public void close() throws Exception {
        workers.forEach(it -> {
            try {
                it.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                Runnable task;
                try {
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    break;
                }
                task.run();
            }
        }
    }
}
