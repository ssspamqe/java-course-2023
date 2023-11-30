package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private final ExecutorService threadPool;
    private final List<Future<Double>> stats = new ArrayList<>();

    public StatsCollector(int nThreads) {
        threadPool = Executors.newFixedThreadPool(nThreads);
    }

    public <T extends Number> int push(MetricType type, T[] array) {
        int id = -1;
        LOCK.writeLock().lock();
        try {
            id = getMetricId();
        } finally {
            LOCK.writeLock().unlock();
        }

        stats.set(id, threadPool.submit(() -> StatsCalculator.getMetric(type, array)));

        return id;
    }

    private synchronized int getMetricId() {
        stats.add(null);
        return stats.size() - 1;
    }

    public List<Future<Double>> getStats() {
        LOCK.readLock().lock();
        try {
            return stats;
        } finally {
            LOCK.readLock().unlock();
        }
    }
}
