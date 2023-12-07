package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ObjectCache {

    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Object object;
    private SoftReference<Map<Method, MethodCache>> methodCachesRef;

    public ObjectCache(Object object) {
        this.object = object;
        methodCachesRef = new SoftReference<>(new HashMap<>());
    }

    public Object readResult(Method method, Object[] args) {
        var methodCaches = methodCachesRef.get();

        if (methodCaches == null) {
            return null;
        }
        return methodCaches.get(method).readResult(args);
    }

    public void writeResult(Method method, Object[] args, Object result) {
        var methodCaches = methodCachesRef.get();

        if (methodCaches == null) {
            methodCachesRef = new SoftReference<>(new HashMap<>());
            methodCaches = methodCachesRef.get();
        }

        if (!methodCaches.containsKey(method)) {
            methodCaches.put(method, new MethodCache(method));
        }
        methodCaches.get(method).writeResult(args, result);
    }
}
