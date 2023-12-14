package edu.hw11;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

enum Appender implements ByteCodeAppender {

    INSTANCE;

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
            LongFibonacci.INSTANCE
        ).apply(mv, implementationContext);

        return new Size(
            operandStackSize.getMaximalSize(),
            instrumentedMethod.getStackSize()
        );
    }
}
