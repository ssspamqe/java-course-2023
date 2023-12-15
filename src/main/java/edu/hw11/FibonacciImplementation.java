package edu.hw11;

import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;

class FibonacciImplementation implements Implementation {

    private FibonacciImplementation() {

    }

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    @Override
    public ByteCodeAppender appender(Target implementationTarget) {
        return LongFibonacciAppender.getInstance();
    }

    private static class SingletonHolder {
        public static final Implementation INSTANCE = new FibonacciImplementation();
    }

    public static Implementation getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
