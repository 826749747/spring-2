package com.harry.spring.aop.Impl;


import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

;

/*
 * s声明为切面：
 * 	1.加入ioc
 *  2.a声明为切面
 *
 * */
@Component
@Aspect
public class LoggingAspect {
	
	//a声明该方法是一个前置通知：在目标开始之前执行
	@Before(value = "execution(public int com.harry.spring.aop.Impl.ArithmeticCaculatorImpl.add(int, int))")
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("before..." + methodName + "begin with" + args);
	}
}
