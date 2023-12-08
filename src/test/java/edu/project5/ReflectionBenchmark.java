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

    @FunctionalInterface
    interface FuncInt {
        MethodType signature = MethodType.methodType(Object.class, Student.class);

        Object invoke(Student student);
    }

    public static void main(String[] args) throws Throwable {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private static Student student;
    private static Method method;
    private static MethodHandle methodHandle;
    private static Function function;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        var lookup = MethodHandles.lookup();
        var methodType = MethodType.methodType(String.class);

        method = Student.class.getMethod("name");

        methodHandle = lookup.findVirtual(Student.class, "name", methodType);

        function = setUpLambdaFunction();
    }

    private Function setUpLambdaFunction() throws Throwable {
        var lookup = MethodHandles.lookup();
        MethodType functionSignature = MethodType.methodType(Object.class, Student.class);
        CallSite secondSite = LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class)),
            functionSignature
        );
        return (Function) secondSite.getTarget().invokeExact();

//        System.out.println(func.apply(student));
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
