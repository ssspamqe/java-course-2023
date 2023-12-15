package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private static Method method;
    private static Class<?> FibonacciClass = FibonacciClassProvider.getFibonacciClass();

    static {
        try {
            method = FibonacciClass.getDeclaredMethod(FibonacciClassProvider.METHOD_NAME, int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 10, 47})
    @DisplayName("Method should return n-th fibonacci number")
    void method_should_returnNthFibonacciNumber(int n) throws InvocationTargetException, IllegalAccessException {
        long correctNumber = getFib(n);

        long returnedNumber = (long) method.invoke(null, n);

        assertThat(returnedNumber).isEqualTo(correctNumber);
    }

    private long getFib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return getFib(n - 1) + getFib(n - 2);
        }
    }
}
