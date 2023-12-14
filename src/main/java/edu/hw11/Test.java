package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class Test {

    public static void main(String[] args) throws Exception {
        var a = new ByteBuddy()
            .subclass(Object.class)
            .defineMethod("calculate", long.class, Visibility.PUBLIC)
            .withParameters(int.class)
            //.method(named("calculate"))
            .intercept(SumImplementation.INSTANCE)
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

        var instance = a.newInstance();
        var method = a.getMethod("calculate", int.class);

        System.out.println(method.invoke(instance, 10));

    }
}
