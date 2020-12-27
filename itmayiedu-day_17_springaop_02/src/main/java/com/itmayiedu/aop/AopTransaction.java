package com.itmayiedu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.itmayiedu.transaction.TransactionUtils;

//切面类  基于手手动事务封装
@Component
@Aspect
public class AopTransaction {
	@Autowired
	private TransactionUtils transactionUtils;

	// TransactionUtils 不要实现为单例子： 如果为单例子的话可能会发生线程安全问题
	// // 异常通知
	@AfterThrowing("execution(* com.itmayiedu.service.UserService.add(..))")
	public void afterThrowing() {
		System.out.println("回滚事务");
		// 获取当前事务 直接回滚
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	// 环绕通知 在方法之前和之后处理事情
	@Around("execution(* com.itmayiedu.service.UserService.add(..))")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// 调用方法之前执行
		System.out.println("开启事务");
		TransactionStatus transactionStatus = transactionUtils.begin();
		proceedingJoinPoint.proceed();// 代理调用方法 注意点： 如果调用方法抛出溢出不会执行后面代码
		// 调用方法之后执行
		System.out.println("提交事务");
		transactionUtils.commit(transactionStatus);
	}
}
