package edu.hw10.Task2;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodCache {

    private final Method method;
    private final Map<List<Object>, Object> cache;

    public MethodCache(Method method) {
        this.method = method;
        cache = new HashMap<>();
    }

    public Object readResult(Object[] args) {
        return cache.get(Arrays.asList(args));
    }

    public void writeResult(Object[] args, Object result) {
        cache.put(Arrays.asList(args), result);
    }
}
