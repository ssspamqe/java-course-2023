package edu.hw11;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class FibonacciClassProvider {

    public static final String CLASS_NAME = "edu.hw11.FibonacciCalculator";
    public static final String METHOD_NAME = "getFibonacciNumber";

    private FibonacciClassProvider() {

    }

    public static Class<?> getFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.STATIC)
            .withParameters(int.class)
            .intercept(FibonacciImplementation.getInstance())
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
    }
}
