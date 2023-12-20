package edu.hw11.FibonacciClassProviderSupport;

import edu.hw11.FibonacciClassProvider;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

class LongFibonacciManipulation implements StackManipulation {

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
        mv.visitVarInsn(Opcodes.ILOAD, 0); //loading method argument to stack
        mv.visitInsn(Opcodes.ICONST_1); //loading 1 to stack

        Label elseLabel = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, elseLabel); //if argument is greater than 1, than jump to "elseLabel"
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L); //loading argument to the stack and casting it to long
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitLabel(elseLabel);
        //checking frame state after jump instruction (ByteBuddy's feature)
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ISUB); //loading arg-1 to the top of the stack
        mv.visitMethodInsn ( //calling method recursively, passing arg-1
            Opcodes.INVOKESTATIC,
            METHOD_OWNER,
            FibonacciClassProvider.METHOD_NAME,
            METHOD_DESCRIPTOR,
            false
        );

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.ISUB); //loading arg-2 to the top of the stack
        mv.visitMethodInsn ( //calling method recursively, passing arg-2
            Opcodes.INVOKESTATIC,
            METHOD_OWNER,
            FibonacciClassProvider.METHOD_NAME,
            METHOD_DESCRIPTOR,
            false
        );

        mv.visitInsn(Opcodes.LADD); //summing up two returned long values and loading it to the top of the stack
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitEnd();

        return new Size(SIZE_IMPACT, MAXIMAL_SIZE);
    }

    private static String getMethodOwner() {
        return String.join("/", FibonacciClassProvider.CLASS_NAME.split("[.]"));
    }

    public static StackManipulation getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        public static final StackManipulation INSTANCE = new LongFibonacciManipulation();
    }
}
