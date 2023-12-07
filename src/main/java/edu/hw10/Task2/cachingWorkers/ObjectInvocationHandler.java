package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectInvocationHandler<T> implements InvocationHandler {

    private static final String DEFAULT_CACHE_DIRECTORY = "./src/main/java/edu/hw10/task2/cachingWorkers/";
    private static final Logger LOGGER = LogManager.getLogger();

    private SoftReference<ObjectCache> cacheRef;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    private T object;

    public ObjectInvocationHandler(T object) {
        this.object = object;
        cacheRef = new SoftReference<>(new ObjectCache(object));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation == null) {
            return method.invoke(proxy, args);
        }

        Object result = readResultFromCache(method, args);
        if (result != null) {
            LOGGER.info("found {} on args {}", result, args);
            return result;
        }

        result = method.invoke(proxy, args);
        writeResultToCache(method, args, result);
        return result;
    }

    private Object readResultFromCache(Method method, Object[] args) {
        lock.readLock().lock();
        try {
            var cache = cacheRef.get();
            if (cacheRef.get() == null) {
                return null;
            }
            return cache.readResult(method, args);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void writeResultToCache(Method method, Object[] args, Object result) {
        lock.writeLock().lock();
        try {
            var cache = cacheRef.get();
            if (cache == null) {
                cacheRef = new SoftReference<>(new ObjectCache(object));
                cache = cacheRef.get();
            }
            cache.writeResult(method, args, result);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
