//package com.itmayiedu.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class AopLog {
//
//	// 前置通知
//	@Before("execution(* com.itmayiedu.service.UserService.add(..))")
//	public void begin() {
//		System.out.println("前置通知");
//	}
//
//	//
//	// 后置通知
//	@After("execution(* com.itmayiedu.service.UserService.add(..))")
//	public void commit() {
//		System.out.println("后置通知");
//	}
//
//	// 运行通知
//	@AfterReturning("execution(* com.itmayiedu.service.UserService.add(..))")
//	public void returning() {
//		System.out.println("运行通知");
//	}
//
//	// 异常通知
//	@AfterThrowing("execution(* com.itmayiedu.service.UserService.add(..))")
//	public void afterThrowing() {
//		System.out.println("异常通知");
//	}
//
//	// 环绕通知
//	@Around("execution(* com.itmayiedu.service.UserService.add(..))")
//	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		System.out.println("环绕通知开始");
//		proceedingJoinPoint.proceed();
//		System.out.println("环绕通知结束");
//	}
//}
