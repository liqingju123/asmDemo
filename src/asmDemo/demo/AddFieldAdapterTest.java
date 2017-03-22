package asmDemo.demo;

import java.io.FileOutputStream;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.util.CheckClassAdapter;

public class AddFieldAdapterTest {

	@Test
	public void test() throws Exception {
		ClassReader cr = new ClassReader("/Users/imac/Example22.class");
		ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "HELL", null, "java/lang/Object", null);
		Method m = Method.getMethod("void <init> ()");
		GeneratorAdapter mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
		mg.loadThis();
		mg.invokeConstructor(Type.getType(Object.class), m);
		mg.returnValue();
		mg.endMethod();

		CheckClassAdapter ca = new CheckClassAdapter(cw);
		AddFieldAdapter af = new AddFieldAdapter(ca, Opcodes.ACC_PUBLIC, "field", "I");
		
		byte[] code = cw.toByteArray();

		FileOutputStream fos = new FileOutputStream("/Users/imac/Example22.class");
		fos.write(code);
		fos.close();
	}

}
