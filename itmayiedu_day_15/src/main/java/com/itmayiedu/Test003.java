package com.itmayiedu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

public class Test003 {

	public static void main(String[] args)
			throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
		ClassPool pool = ClassPool.getDefault();
		// 需要加载类信息
		CtClass userClass = pool.get("com.itmayiedu.User");
		// 需要添加的方法
		CtMethod m = new CtMethod(CtClass.intType, "add", new CtClass[] { CtClass.intType, CtClass.intType },
				userClass);
		// 方法权限
		m.setModifiers(Modifier.PUBLIC);
		// 方法体内容
		m.setBody("{System.out.println(\"Test003\"); return $1+$2;}");
		userClass.addMethod(m);
		userClass.writeFile("F:/test");// 将构造好的类写入到F:\test 目录下
		// 使用反射技术执行方法
		Class clazz = userClass.toClass();
		Object obj = clazz.newInstance(); // 通过调用User 无参构造函数
		Method method = clazz.getDeclaredMethod("add", int.class, int.class);
		Object result = method.invoke(obj, 200, 300);
		System.out.println(result);

    // 1.使用反射技术执行某方法
//    Class<?> forName = Class.forName("com.itmayiedu.Test003");
//    Object newInstance = forName.newInstance();
//    Method method = forName.getDeclaredMethod("sum", int.class, int.class);
//    method.invoke(newInstance, 1, 5);
	}

}
