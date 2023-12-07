package edu.hw10.Task2.cachingWorkers;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodCache {

    private final Method method;
    private SoftReference<Map<List<Object>, Object>> cacheRef;

    public MethodCache(Method method) {
        this.method = method;
        cacheRef = new SoftReference<>(new HashMap<>());
    }

    public Object readResult(Object[] args) {
        var cache = cacheRef.get();

        if (cache == null) {
            return null;
        }
        return cache.get(Arrays.asList(args));

    }

    public void writeResult(Object[] args, Object result) {
        var cache = cacheRef.get();

        if (cache == null) {
            cacheRef = new SoftReference<>(new HashMap<>());
            cache = cacheRef.get();
        }
        cache.put(Arrays.asList(args), result);
    }
}
