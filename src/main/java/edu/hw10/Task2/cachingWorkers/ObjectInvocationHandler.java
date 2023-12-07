package edu.hw10.Task2.cachingWorkers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectInvocationHandler<T> implements InvocationHandler {

    private static final String DEFAULT_CACHE_FILE_PATH = "./src/main/java/edu/hw10/task2/cachingWorkers/cache.txt";
    private static final Logger LOGGER = LogManager.getLogger();

    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final String cacheFilePath;
    private final T object;

    private SoftReference<ObjectCache> cacheRef;

    public ObjectInvocationHandler(T object, String cacheFilePath) {
        this.object = object;
        this.cacheFilePath = cacheFilePath;
        cacheRef = new SoftReference<>(new ObjectCache(object));
    }

    public ObjectInvocationHandler(T object) {
        this(object, DEFAULT_CACHE_FILE_PATH);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        var cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation == null) {
            return method.invoke(proxy, args);
        }

        Object result = readResultFromCache(method, args);
        if (result != null) {
            LOGGER.info("{}: found {} on args {}", method, result, args);
            return result;
        }
        LOGGER.info("{}: nothing on args {}", method, args);

        result = method.invoke(object, args);
        writeResultToCache(method, args, result);
        if (cacheAnnotation.persist()) {
            writeToCacheFile(getCacheLog(method, args, result));
        }
        return result;
    }

    private Object readResultFromCache(Method method, Object[] args) {
        lock.readLock().lock();
        try {
            var cache = cacheRef.get();
            if (cacheRef.get() == null) {
                return null;
            }
            var val = cache.readResult(method, args);
            return val;
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

    private void writeToCacheFile(String line) {
        try (var fileWriter = new FileWriter(cacheFilePath, true);
             BufferedWriter fileOut = new BufferedWriter(fileWriter)) {
            fileOut.append(line + "\n");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getCacheLog(Method method, Object[] args, Object result) {
        return STR. "\{ method }: \{ Arrays.asList(args) } -> [\{ result }]" ;
    }
}
