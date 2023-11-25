package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThreadPool implements ThreadPool {

    private final static Logger LOGGER = LogManager.getLogger();

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition queueIsEmpty = lock.newCondition();

    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;

    private boolean closing = false;

    public MyThreadPool(int poolSize) {
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
        if (closing) {
            throw new RuntimeException("Thread pool is closing now...");
        }
        taskQueue.add(newTask);
    }

    @Override
    public void close() throws Exception {
        closing = true;

        waitEmptyQueue();

        workers.forEach(it -> {
            try {
                if (it.isWaitingForWork()) {
                    it.interrupt();
                }
                it.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void waitEmptyQueue() {
        lock.lock();
        try {
            queueIsEmpty.await();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }


    

    private class Worker extends Thread {
        private boolean waitingForWork = false;

        public void run() {
            while (!closing || !taskQueue.isEmpty()) {
                Runnable task;
                try {
                    waitingForWork = true;
                    task = taskQueue.take();
                    waitingForWork = false;
                } catch (InterruptedException e) {
                    continue;
                }
                signalIfNoTasks();
                task.run();
            }

        }

        public boolean isWaitingForWork() {
            return waitingForWork;
        }

        private void signalIfNoTasks() {
            if (taskQueue.isEmpty()) {
                lock.lock();
                try {
                    queueIsEmpty.signal();
                } catch (Exception ex) {
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

