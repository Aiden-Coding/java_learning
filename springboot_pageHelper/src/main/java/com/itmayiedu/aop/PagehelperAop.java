package com.itmayiedu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PagehelperAop {
	@Pointcut("execution(public * com.itmayiedu.service.*.*(..))")
	public void pagehelperAop() {
	}

	@Around("pagehelperAop()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		pjp.getTarget();
		log.info("方法之前..");
		Object proceed = pjp.proceed();
		log.info("方法之后..");
		return proceed;
	}

}
