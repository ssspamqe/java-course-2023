package edu.hw10.Task1;

import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final RandomObjectGenerator ROG = new RandomObjectGenerator();

    public Object nextObject(Class<?> objectClass) {
        var constructor = getConstructorWithBiggestParameterAmount(objectClass);
        var parameterTypes = constructor.getParameterTypes();
        var arguments = generateArguments(parameterTypes);

        try {
            return constructor.newInstance(arguments.toArray(new Object[0]));
        } catch (Exception ex) {
            LOGGER.warn(ex);
            return null;
        }

    }

    public Object nextObject(Class<?> objectClass, String fabricMethodName) {
        var fabricMethod = getMethodWithBiggestParametersAmountWithName(fabricMethodName, objectClass);
        var parameterTypes = fabricMethod.getParameterTypes();
        var rawArguments = generateArguments(parameterTypes);

        try {
            return fabricMethod.invoke(null,rawArguments.toArray(new Object[0]));
        } catch (Exception ex) {
            LOGGER.warn(ex);
            return null;
        }
    }

    private Method getMethodWithBiggestParametersAmountWithName(@NotNull String name, Class<?> objectClass) {
        var methods = objectClass.getMethods();
        return Arrays.stream(methods)
            .filter(it -> it.getName().equals(name))
            .reduce((x, y) -> {
                if (x.getParameterCount() > y.getParameterCount()) {
                    return x;
                } else {
                    return y;
                }
            })
            .orElseThrow(()->new IllegalArgumentException(objectClass + "do not have static fabric method with such name"));
    }

    private Constructor<?> getConstructorWithBiggestParameterAmount(Class<?> objectClass) {
        var constructors = objectClass.getConstructors();
        var biggestConstructor = constructors[0];

        for (var c : constructors) {
            if (c.getParameterCount() > biggestConstructor.getParameterCount()) {
                biggestConstructor = c;
            }
        }

        return biggestConstructor;
    }

    private List<Object> generateArguments(Class<?>[] argumentTypes) {
        List<Object> arguments = new ArrayList<>();
        for (var type : argumentTypes) {
            if (type.isPrimitive()) {
                arguments.add(generatePrimitiveInstance(type));
            } else {
                try {
                    arguments.add(ROG.nextObject(type));
                } catch (Exception ex) {
                    LOGGER.warn(ex);
                    arguments.add(null);
                }
            }
        }
        return arguments;
    }

    public Object generatePrimitiveInstance(Class<?> primitive) {
        if (primitive == byte.class || primitive == Byte.class) {
            return RANDOM.nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        } else if (primitive == short.class || primitive == Short.class) {
            return RANDOM.nextInt(Short.MIN_VALUE, Short.MAX_VALUE);
        } else if (primitive == int.class || primitive == Integer.class) {
            return RANDOM.nextInt();
        } else if (primitive == long.class || primitive == Long.class) {
            return RANDOM.nextLong();
        } else if (primitive == float.class || primitive == Float.class) {
            return RANDOM.nextFloat();
        } else if (primitive == double.class || primitive == Double.class) {
            return RANDOM.nextDouble();
        } else if (primitive == char.class || primitive == Character.class) {
            return (char) RANDOM.nextLong(0, (long) Math.pow(2, 16));
        } else if (primitive == boolean.class || primitive == Boolean.class) {
            return RANDOM.nextBoolean();
        }
        throw new IllegalArgumentException("Given class is not a primitive one");
    }

    //TODO singleton???
}
