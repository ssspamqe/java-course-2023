package edu.hw11;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

enum LongFibonacci implements StackManipulation {

    INSTANCE; // singleton

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Size apply(
        MethodVisitor mv,
        Implementation.Context implementationContext
    ) {
        mv.visitCode();

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L);

        mv.visitInsn(Opcodes.LCONST_1);

        mv.visitInsn(Opcodes.LCMP);

        Label elseLabel = new Label();
        mv.visitJumpInsn(Opcodes.IFGT, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.LRETURN);
        mv.visitLabel(elseLabel);

        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitInsn(Opcodes.L2I);
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "edu/hw11/FibonacciCalculator",
            FibonacciClassProvider.methodName,
            "(I)J",
            false
        );

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitInsn(Opcodes.L2I);
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "edu/hw11/FibonacciCalculator",
            FibonacciClassProvider.methodName,
            "(I)J",
            false
        );

        mv.visitInsn(Opcodes.LADD);
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitMaxs(20, 20);
        mv.visitEnd();

        return new Size(20, 20);
    }
}
