package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//This class is thread safe
public class ObjectCache {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(true);

    private final Object object;
    private SoftReference<Map<Method, MethodCache>> methodCachesRef;

    public ObjectCache(Object object) {
        this.object = object;
        methodCachesRef = new SoftReference<>(new HashMap<>());
    }

    public Object readResult(Method method, Object[] args) {
        var methodCaches = getCurrentMethodCaches();

        LOCK.readLock().lock();
        try {
            if (methodCaches == null) {
                return null;
            }
            return methodCaches.get(method).readResult(args);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    public void writeResult(Method method, Object[] args, Object result) {
        var methodCaches = getCurrentMethodCaches();

        LOCK.writeLock().lock();
        try {
            if (methodCaches == null) {
                methodCachesRef = new SoftReference<>(new HashMap<>());
                methodCaches = methodCachesRef.get();
            }

            if (!methodCaches.containsKey(method)) {
                methodCaches.put(method, new MethodCache(method));
            }
            methodCaches.get(method).writeResult(args, result);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    private Map<Method, MethodCache> getCurrentMethodCaches() {
        LOCK.readLock().lock();
        try {
            return methodCachesRef.get();
        } finally {
            LOCK.readLock().unlock();
        }
    }
}
