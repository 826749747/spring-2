package com.harry.spring.aop.Impl.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class VlidationAspect {

	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate: " + Arrays.toString(joinPoint.getArgs()));
	}
}
