package edu.hw10.Task2.cachingWorkers;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy(Object object) {

    }

    public static Object create(Object object, String cacheFilePath) {
        var classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new ObjectInvocationHandler<>(object, cacheFilePath));
    }

    public static Object create(Object object) {
        var classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new ObjectInvocationHandler<>(object));
    }

}
