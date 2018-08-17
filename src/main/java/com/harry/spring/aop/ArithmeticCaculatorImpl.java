package com.harry.spring.aop;

public class ArithmeticCaculatorImpl implements ArithmeticCaculator {

	public int add(int a, int b) {

		return a + b ;
	}

	public int sub(int a, int b) {

		return a - b;
	}

	public int mul(int a, int b) {

		return a * b;
	}

	public int div(int a, int b) {
		return a / b;
	}

}
