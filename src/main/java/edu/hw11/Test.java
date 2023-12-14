package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Test {

    public static void main(String[] args) throws Exception {
        abstract class SumExample {
            public abstract int calculate(int a);

            @Override
            public String toString() {
                return "131231";
            }
        }

        var a = new ByteBuddy()
            .subclass(SumExample.class)
            .method(named("calculate"))
            .intercept(SumImplementation.INSTANCE)
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

        var instance = a.newInstance();

        System.out.println(instance.calculate(19));
    }
}
