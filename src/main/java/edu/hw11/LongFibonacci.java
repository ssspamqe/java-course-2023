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
        //mv.visitVarInsn(Opcodes.ILOAD, 1);
        Label elseLabel = new Label();
        mv.visitJumpInsn(Opcodes.IFGT, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitLabel(elseLabel);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hw11/mult/Fib", "fib", "(I)I", false);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hw11/mult/Fib", "fib", "(I)I", false);
        mv.visitInsn(Opcodes.IADD);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(20, 20);
        mv.visitEnd();

//        mv.visitCode();
//        Label elseLabel = new Label();
//        mv.visitVarInsn(Opcodes.ILOAD, 1);
//        mv.visitJumpInsn(Opcodes.IFGT, elseLabel);
//        mv.visitVarInsn(Opcodes.ILOAD, 1);
//        mv.visitInsn(Opcodes.IRETURN);
//        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//        mv.visitLabel(elseLabel);
//        mv.visitVarInsn(Opcodes.ILOAD, 1);
//        mv.visitInsn(Opcodes.ICONST_1);
//        mv.visitInsn(Opcodes.ISUB);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "src/main/java/edu/hw11/mult/Fib", "fib", "(I)I", false);
//        mv.visitVarInsn(Opcodes.ILOAD, 1);
//        mv.visitInsn(Opcodes.ICONST_2);
//        mv.visitInsn(Opcodes.ISUB);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "src/main/java/edu/hw11/mult/Fib", "fib", "(I)I", false);
//        mv.visitInsn(Opcodes.IADD);
//        mv.visitInsn(Opcodes.IRETURN);
//        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//        mv.visitMaxs(2, 2);
//        mv.visitEnd();

        return new Size(20, 20);
    }
}
