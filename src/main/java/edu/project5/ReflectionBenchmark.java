package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {

    private static final int N_FORKS = 1;
    private static final int N_WARMUP_FORKS = 1;
    private static final int N_WARMUP_ITERATIONS = 5;
    private static final int N_MEASUREMENT_ITERATIONS = 5;

    private static final TimeValue WARMUP_TIME = TimeValue.seconds(5);
    private static final TimeValue MEASUREMENT_TIME = TimeValue.seconds(5);

    private static final String FIELD_NAME = "name";

    private static Student student;
    private static Method method;
    private static MethodHandle methodHandle;
    private static Function function;

    public static void launch() throws Throwable {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(N_FORKS)
            .warmupForks(N_WARMUP_FORKS)
            .warmupIterations(N_WARMUP_ITERATIONS)
            .warmupTime(WARMUP_TIME)
            .measurementIterations(N_MEASUREMENT_ITERATIONS)
            .measurementTime(MEASUREMENT_TIME)
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");

        method = Student.class.getMethod(FIELD_NAME);

        methodHandle = getMethodHandle();

        function = getLambdaFunction();
    }

    private MethodHandle getMethodHandle() throws Throwable {
        var lookup = MethodHandles.lookup();
        var methodType = MethodType.methodType(String.class);
        return lookup.findVirtual(Student.class, FIELD_NAME, methodType);
    }

    private Function<Student, String> getLambdaFunction() throws Throwable {
        var functionInterfaceMethodType = MethodType.methodType(Function.class);
        var lambdaType = MethodType.methodType(Object.class, Object.class);
        var lookup = MethodHandles.lookup();
        var studentMethod = lookup.findVirtual(Student.class, FIELD_NAME, MethodType.methodType(String.class));

        MethodType functionSignature = MethodType.methodType(Object.class, Student.class);
        CallSite secondSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            functionInterfaceMethodType,
            lambdaType,
            studentMethod,
            functionSignature
        );
        return (Function<Student, String>) secondSite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole blackhole) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void lambdaFunction(Blackhole blackhole) throws Throwable {
        String name = (String) function.apply(student);
        blackhole.consume(name);
    }
}
