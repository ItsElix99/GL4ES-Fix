package com.itselix99.gl4esfix;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TessellatorTransformer implements ClassFileTransformer {
    private final String tessellatorName;

    public TessellatorTransformer(String tessellatorName) {
        this.tessellatorName = tessellatorName;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.replace("/", ".").equals(this.tessellatorName)) {
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);

            ClassVisitor visitor = new TessellatorClassVisitor(writer);
            reader.accept(visitor, 0);
            return writer.toByteArray();
        }

        return classfileBuffer;
    }
}