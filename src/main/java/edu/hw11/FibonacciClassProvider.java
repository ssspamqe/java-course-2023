package edu.hw11;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class FibonacciClassProvider {

    public static final String className = "edu.hw11.FibonacciCalculator";
    public static final String methodName = "getFibonacciNumber";

    public static Class<?> getFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(className)
            .defineMethod(methodName, long.class, Modifier.STATIC)
            .withParameters(int.class)
            .intercept(FibonacciImplementation.INSTANCE)
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
    }

//    public static void main(String[] args) throws Exception {
//        var a = new ByteBuddy()
//            .subclass(Object.class)
//            .name("edu.hw11.FibonacciCalculator")
//            .defineMethod("calculate", long.class, Modifier.STATIC)
//            .withParameters(int.class)
//            .intercept(FibonacciImplementation.INSTANCE)
//            .make()
//            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
//            .getLoaded();
//
//        var method = a.getDeclaredMethod("calculate", int.class);
//
//        System.out.println(method.invoke(null, 47));
//    }
}
