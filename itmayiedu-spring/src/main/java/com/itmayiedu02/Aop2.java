
package com.itmayiedu02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

//定义切面
//注入Spring容器
public class Aop2 {

	@Autowired
	private TransactionUtils transactionUtils;

	public void begin() {
		System.out.println("......前置通知......");
	}

	public void after() {
		System.out.println(".......后置通知......");
	}

	public void afterReturning() {
		System.out.println(".......运行通知......");
	}

	public void afterThrowing() {
		System.out.println(".......异常通知......");
	}

	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		TransactionStatus begin = transactionUtils.begin();
		System.out.println("我是环绕通知-开启事物");
		try {
			proceedingJoinPoint.proceed();
			transactionUtils.commit(begin);
			System.out.println("我是环绕通知-提交事物");
		} catch (Exception e) {
			System.out.println("我是环绕通知-回滚事物");
			transactionUtils.rollback(begin);
		}
	}

}
