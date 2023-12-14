package edu.hw11;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class Test {

    public static void main(String[] args) throws Exception {
        var a = new ByteBuddy()
            .subclass(Object.class)
            .name("edu.hw11.FibonacciCalculator")
            .defineMethod("calculate", long.class, Modifier.STATIC)
            .withParameters(int.class)
            .intercept(FibonacciImplementation.INSTANCE)
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

        var method = a.getDeclaredMethod("calculate", int.class);

        System.out.println(method.invoke(null, 47));
    }
}
