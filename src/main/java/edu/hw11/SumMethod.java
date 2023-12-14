package edu.hw11;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;

enum SumMethod implements ByteCodeAppender {

    INSTANCE; // singleton

    @Override
    public Size apply(
        MethodVisitor mv,
        Implementation.Context implementationContext,
        MethodDescription instrumentedMethod
    ) {
        if (!instrumentedMethod.getReturnType().asErasure().represents(int.class)) {
            throw new IllegalArgumentException(instrumentedMethod + " must return int");
        }
        StackManipulation.Size operandStackSize = new StackManipulation.Compound(
            MethodVariableAccess.INTEGER.loadFrom(1),
//
            LongFibonacci.INSTANCE

            //MethodReturn.INTEGER
        ).apply(mv, implementationContext);

        return new Size(
            operandStackSize.getMaximalSize(),
            instrumentedMethod.getStackSize()
        );
    }
}
