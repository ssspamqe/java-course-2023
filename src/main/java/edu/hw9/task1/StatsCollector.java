package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private final Executor threadPool;
    private final List<Double> stats = new ArrayList<>();

    public StatsCollector(int nThreads) {
        threadPool = Executors.newFixedThreadPool(nThreads);
    }

    public <T extends Number> int push(MetricType type, T[] array) {
        int id;
        synchronized (this) {
            id = stats.size();
            stats.add(0.0);
        }

        threadPool.execute(() -> {
            LOCK.writeLock().lock();
            try {
                stats.set(id, StatsCalculator.getMetric(type, array));
            } finally {
                LOCK.writeLock().unlock();
            }
        });

        return id;
    }

    public List<Double> getStats() {
        LOCK.readLock().lock();
        try {
            return stats;
        } finally {
            LOCK.readLock().unlock();
        }
    }
}
