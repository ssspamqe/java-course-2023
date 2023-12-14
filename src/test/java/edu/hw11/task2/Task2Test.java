package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("ByteBuddy should modify classes' behaviour")
    void byteBuddy_should_modifyClassesBehaviour() {
        int a = 2;
        int b = 5;

        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.task2.Calculator")
            .resolve();
        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("getSum")).intercept(MethodDelegation.to(CalculatorInterceptor.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);

        int result = Calculator.getSum(a, b);
        assertThat(result).isEqualTo(a * b);
    }

}
