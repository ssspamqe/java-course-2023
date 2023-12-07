package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class is thread safe, uses SoftReferences
 */
public class MethodCache {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(true);

    private final Method method;
    private SoftReference<Map<List<Object>, Object>> cacheRef;

    public MethodCache(Method method) {
        this.method = method;
        cacheRef = new SoftReference<>(new HashMap<>());
    }

    public Object readResult(Object[] args) {
        var cache = getCurrentCache();

        LOCK.readLock().lock();
        try {
            if (cache == null) {
                return null;
            }
            return cache.get(Arrays.asList(args));
        } finally {
            LOCK.readLock().unlock();
        }
    }

    public void writeResult(Object[] args, Object result) {
        var cache = getCurrentCache();

        LOCK.writeLock().lock();
        try {
            if (cache == null) {
                cacheRef =new SoftReference<>(new HashMap<>());
                cache = cacheRef.get();
            }
            cache.put(Arrays.asList(args), result);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    private Map<List<Object>, Object> getCurrentCache() {
        LOCK.readLock().lock();
        try {
            return cacheRef.get();
        } finally {
            LOCK.readLock().unlock();
        }
    }
}
