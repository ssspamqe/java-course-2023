package edu.hw11.FibonacciClassProviderSupport;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

class LongFibonacciAppender implements ByteCodeAppender {

    private LongFibonacciAppender() {

    }

    @Override
    public Size apply(
        MethodVisitor mv,
        Implementation.Context implementationContext,
        MethodDescription instrumentedMethod
    ) {
        if (!instrumentedMethod.getReturnType().asErasure().represents(long.class)) {
            throw new IllegalArgumentException(instrumentedMethod + " must return long");
        }

        StackManipulation.Size operandStackSize = new StackManipulation.Compound(
            LongFibonacciManipulation.getInstance()
        ).apply(mv, implementationContext);

        return new Size(
            operandStackSize.getMaximalSize(),
            instrumentedMethod.getStackSize()
        );
    }

    public static ByteCodeAppender getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        public static final ByteCodeAppender INSTANCE = new LongFibonacciAppender();
    }
}
