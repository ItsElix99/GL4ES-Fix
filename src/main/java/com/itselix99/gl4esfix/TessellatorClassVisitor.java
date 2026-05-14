package com.itselix99.gl4esfix;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TessellatorClassVisitor extends ClassVisitor {

    public TessellatorClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    public MethodVisitor visitMethod(final int access, final String name, final String descriptor, final String signature, final String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        if (name.equals("<clinit>")) {
            return new MethodVisitor(Opcodes.ASM9, methodVisitor) {
                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                    if (opcode == Opcodes.PUTSTATIC && name.equals("b") && descriptor.equals("Z")) {
                        super.visitInsn(Opcodes.ICONST_0);
                        super.visitFieldInsn(opcode, owner, name, descriptor);
                        System.out.println("[GL4ES Fix] Fix applied successfully for Minecraft " + GL4ESFix.mcVersion.replace("inf_", "inf "));
                        return;
                    }

                    super.visitFieldInsn(opcode, owner, name, descriptor);
                }
            };
        }

        return methodVisitor;
    }
}