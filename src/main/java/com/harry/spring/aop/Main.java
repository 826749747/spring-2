package com.harry.spring.aop;

import com.harry.spring.aop.Impl.ArithmeticCaculator;
import com.harry.spring.aop.Impl.ArithmeticCaculatorImpl;

public class Main {
	
	public static void main(String[] args) {
		
		ArithmeticCaculator target = new ArithmeticCaculatorImpl();
		ArithmeticCaculator proxy = new ArithmeticCaculatorLoggingProxy(target).getLoggingProxy();
		
		int result = proxy.add(1, 2);
		System.out.println(result);
		result = proxy.mul(1, 2);
		System.out.println(result);
		
	}
}
