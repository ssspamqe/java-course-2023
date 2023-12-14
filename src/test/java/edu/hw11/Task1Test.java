package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("DynamicClass::toString() should return HelloWorld")
    void dynamicClass_toString_should_returnHelloWorld() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello World"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        var instance = dynamicType.newInstance();
        assertThat(instance.toString()).isEqualTo("Hello World");
    }
}
