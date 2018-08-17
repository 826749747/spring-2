 package com.harry.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.harry.spring.aop.Impl.ArithmeticCaculator;

public class ArithmeticCaculatorLoggingProxy {
	
	private ArithmeticCaculator target;
	
	public ArithmeticCaculatorLoggingProxy(ArithmeticCaculator target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public ArithmeticCaculator getLoggingProxy() {
		
		ArithmeticCaculator proxy = null;
		
		// 5代理对象由哪一个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		// 5代理对象的类型，即其中有哪些方法
		Class [] interfaces = new Class[] {ArithmeticCaculator.class};
		// 5当调用代理对象其中的方法时，该执行的代码
		InvocationHandler handler = new InvocationHandler() {
			/**
			 *  proxy:正在返回的那个代理对象。一般情况下，在Invoke方法中东不使用该对象
			 *  method:正在被调用的方法
			 *  args：调用方法时，传入的参数
			 * */
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				
				String methodName = method.getName();
				// ..a日志
				System.out.println("The method " + methodName + " begin with" + Arrays.asList(args));
				// ..b执行方法
				Object result = method.invoke(target, args);
				System.out.println("The method " + methodName + " end with " + result);
				return 0;
			}
		};
		
		proxy = (ArithmeticCaculator)Proxy.newProxyInstance(loader, interfaces, handler);
		return proxy;
	}
}
