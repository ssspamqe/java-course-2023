package edu.hw10.Task2.cachingWorkers;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy(Object object){


    }

    public static <T> T create(T object){
        var classLoader  = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, interfaces,new ObjectInvocationHandler(object));
    }

}
