package edu.hw11;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

class LongFibonacci implements StackManipulation {

    private static final int SIZE_IMPACT = 5;
    private static final int MAXIMAL_SIZE = 6;

    private static final String METHOD_DESCRIPTOR = "(I)J";
    private static final String METHOD_OWNER = getMethodOwner();

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
            METHOD_OWNER,
            FibonacciClassProvider.METHOD_NAME,
            METHOD_DESCRIPTOR,
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
            METHOD_OWNER,
            FibonacciClassProvider.METHOD_NAME,
            METHOD_DESCRIPTOR,
            false
        );

        mv.visitInsn(Opcodes.LADD);
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitEnd();

        return new Size(SIZE_IMPACT, MAXIMAL_SIZE);
    }

    private static String getMethodOwner() {
        return String.join("/", FibonacciClassProvider.CLASS_NAME.split("[.]"));
    }

    private static class SingletonHolder {
        public static final StackManipulation INSTANCE = new LongFibonacci();
    }

    public static StackManipulation getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
