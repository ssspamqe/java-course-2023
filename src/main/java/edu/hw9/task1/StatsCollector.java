package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final ExecutorService threadPool;
    private final List<Future<Double>> stats = new ArrayList<>();

    public StatsCollector(int nThreads) {
        threadPool = Executors.newFixedThreadPool(nThreads);
    }

    public <T extends Number> int push(MetricType type, T[] array) {
        int id = -1;
        lock.writeLock().lock();
        try {
            id = stats.size();
            stats.add(threadPool.submit(() -> StatsCalculator.getMetric(type, array)));
        } finally {
            lock.writeLock().unlock();
        }

        return id;
    }

    public List<Future<Double>> getStats() {
        lock.readLock().lock();
        try {
            return stats;
        } finally {
            lock.readLock().unlock();
        }
    }
}
