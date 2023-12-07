package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ObjectCache {

    private final Object object;
    private final Map<Method, MethodCache> methodCaches = new HashMap<>();

    public ObjectCache(Object object) {
        this.object = object;
    }

    public Object readResult(Method method, Object[] args) {
        return methodCaches.get(method).readResult(args);
    }

    public void writeResult(Method method, Object[] args, Object result) {
        methodCaches.get(method).writeResult(args, result);
    }
}
