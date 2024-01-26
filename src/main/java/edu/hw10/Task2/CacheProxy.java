package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy(Object object) {

    }

    public static Object create(Object object, String cacheFilePath) {
        return getProxy(object, new ObjectInvocationHandler<>(object, cacheFilePath));
    }

    public static Object create(Object object) {
        return getProxy(object, new ObjectInvocationHandler<>(object));
    }

    private static Object getProxy(
        Object object,
        ObjectInvocationHandler<?> invocationHandler
    ) {
        var classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

}
