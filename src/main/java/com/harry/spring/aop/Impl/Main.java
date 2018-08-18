package com.harry.spring.aop.Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {


		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ArithmeticCaculator arithmeticCaculator = ctx.getBean(ArithmeticCaculator.class);
		
		System.out.println(arithmeticCaculator.getClass().getName());
		int result = arithmeticCaculator.add(1, 2);

		System.out.println(result);
		result = arithmeticCaculator.div(1000, 10);
		System.out.println(result);
	}
}