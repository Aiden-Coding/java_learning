
package com.itmayiedu02;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop02 {

	public void begin() {
		System.out.println("前置通知....");
	}

	public void commit() {
		System.out.println("后置通知....");
	}

	public void afterRun() {
		System.out.println("运行通知....");
	}

	public void afterThrowing() {
		System.out.println("异常通知");
	}

	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("我是环绕通知-前");
		proceedingJoinPoint.proceed();
		System.out.println("我是环绕通知-后");
	}
}
