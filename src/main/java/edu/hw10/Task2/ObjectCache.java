package edu.hw10.Task2;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectCache {

    private final Object object;
    private final Map<Method, MethodCache> methodCaches;

    public ObjectCache(Object object) {
        this.object = object;
        methodCaches = new HashMap<>();
    }

    public Object readResult(Method method, Object[] args) {
        var methodCache = methodCaches.get(method);
        if (methodCache == null) {
            return null;
        }
        return methodCache.readResult(args);
    }

    public void writeResult(Method method, Object[] args, Object result) {
        methodCaches.computeIfAbsent(method, MethodCache::new);
        methodCaches.get(method).writeResult(args, result);
    }
}
