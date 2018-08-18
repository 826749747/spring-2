package com.harry.spring.aop.Impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class VlidationAspect {

	@Before("LoggingAspect.declareJointPoint()")
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate: " + Arrays.toString(joinPoint.getArgs()));
	}
}
