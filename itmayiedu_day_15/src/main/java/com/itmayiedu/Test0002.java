package com.itmayiedu;

import java.io.IOException;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Test0002 {

	public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
		ClassPool pool = ClassPool.getDefault();
		CtClass userClass = pool.get("com.itmayiedu.User");
		byte[] bytecode = userClass.toBytecode();
		System.out.println(Arrays.toString(bytecode));
		System.out.println(userClass.getName()); // 获取类名
		System.out.println(userClass.getSimpleName()); // 获取简要类名

	}

}
