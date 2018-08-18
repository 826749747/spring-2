package com.harry.spring.aop.Impl.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

	public void declareJointPoint() {
	}

	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();

		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("before..." + methodName + " begin with " + args);
	}

	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();

		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("end..." + methodName + " End with " + args);
	}

	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();

//		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("Returning.." + methodName + " End with " + result);
	}

	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Throwing " + methodName + " End with " + ex);
	}


	public Object arroundMethod(ProceedingJoinPoint pjPoint) {
//		System.out.println("aroudMethod...");
//		return 100;
		
		Object result = null;
		String methodName = pjPoint.getSignature().getName();
		//执行目标方法
		try {
			//前置通知
			System.out.println("The method " + methodName + " begin with "+ Arrays.asList(pjPoint.getArgs()));
			result = pjPoint.proceed();
			//后置通知
			System.out.println("The method"+methodName+" ends with " + result);
			
		} catch (Throwable e) {
			// TODO: handle exception
			// 异常通知
			System.out.println("The method"+methodName+" occurs excption: " + e);
		}
		
		//后置通知
		System.out.println("The method " + methodName +"ends");
		return result;
	}
}
