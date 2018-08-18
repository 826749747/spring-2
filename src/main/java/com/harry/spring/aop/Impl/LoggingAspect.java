package com.harry.spring.aop.Impl;


import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

;

/*
 * s声明为切面：
 * 	1.加入ioc
 *  2.a声明为切面
 *
 * */
@Order(2)
@Component
@Aspect
public class LoggingAspect {
	
	/**
	 * 定义一个方法，用于声明切入点表达式。一般的，该方法中再不需要添加其他代码
	 * 使用@Pointcut来声明切入点表达式
	 * 后面其他的通知直接使用方法名来引用当前切入点表达式
	 * */
	@Pointcut("execution(public int com.harry.spring.aop.Impl.ArithmeticCaculatorImpl.*(..))")
	public void declareJointPoint() {	
	}
	
	//a声明该方法是一个前置通知：在目标开始之前执行
	@Before("declareJointPoint()")
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("before..." + methodName + " begin with " + args);
	}
	
	//a后置通知：在目标方法执行后（无论是否发生异常），执行的通知
	//b在后置通知中还不能访问目标方法执行的结果。
	@After(value = "declareJointPoint()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("end..." + methodName + " End with " + args);
	}
	
	//a返回通知
	//b方法正常执行结束后执行的代码
	//c可以访问到方法的返回值的
	//d返回值通过returing元素配置
	@AfterReturning(value = "declareJointPoint()",returning = "result")
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		
//		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("end..." + methodName + " End with " + result);
	}
	
	//异常通知
	/*
	 * z在目标方法出现异常时会执行的代码。
	 * 可以访问到异常对象；且可指定异常类型
	 * */
	@AfterThrowing(value = "declareJointPoint()",throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint,Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("end..." + methodName + " End with " + ex);
	}
	
	//环绕通知:需要携带ProceedingJoinPoint类型参数
	//环绕通知类似于动态代理的全过程：ProceedingJoinPoint 类型的额参数可以决定是否执行目标方法
	//且焕然通知必须有返回值，返回值即目标方法的返回值
//	@Around(value = "execution(public int com.harry.spring.aop.Impl.ArithmeticCaculatorImpl.*(int, int))")
//	public Object arroundMethod(ProceedingJoinPoint pjPoint) {
////		System.out.println("aroudMethod...");
////		return 100;
//		
//		Object result = null;
//		String methodName = pjPoint.getSignature().getName();
//		//执行目标方法
//		try {
//			//前置通知
//			System.out.println("The method " + methodName + " begin with "+ Arrays.asList(pjPoint.getArgs()));
//			result = pjPoint.proceed();
//			//后置通知
//			System.out.println("The method"+methodName+" ends with " + result);
//			
//		} catch (Throwable e) {
//			// TODO: handle exception
//			// 异常通知
//			System.out.println("The method"+methodName+" occurs excption: " + e);
//		}
//		
//		//后置通知
//		System.out.println("The method " + methodName +"ends");
//		return result;
//	}
}
