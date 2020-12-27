package com.itmayiedu;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Test001 {

	public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
		ClassPool pool = ClassPool.getDefault();
		// 创建class文件
		CtClass userClass = pool.makeClass("com.itmayiedu.entity.User");
		// 创建id属性
		CtField idField = CtField.make("private Integer id;", userClass);
		// 创建name属性
		CtField nameField = CtField.make("private Integer name;", userClass);
		// 添加属性
		userClass.addField(idField);
		// 添加属性
		userClass.addField(nameField);
		// 创建方法
		CtMethod getIdMethod = CtMethod.make("public Integer getId() {return id;}", userClass);
		// 创建方法
		CtMethod setIdMethod = CtMethod.make("public void setId(Integer id) { this.id = id; }", userClass);
		// 添加方法
		userClass.addMethod(getIdMethod);
		// 添加方法
		userClass.addMethod(setIdMethod);
		// 添加构造器
		CtConstructor ctConstructor = new CtConstructor(new CtClass[] { CtClass.intType, pool.get("java.lang.String") },
				userClass);
		// 创建Body
		ctConstructor.setBody("	{this.id = id;this.name = name;}");
		userClass.addConstructor(ctConstructor);
		userClass.writeFile("F:/test");// 将构造好的类写入到F:\test 目录下
	}

}
