package com.duyj.work.jdk.reflection;

import java.lang.reflect.Method;

public class InvokeTest {
	
	private String ass;
	
	public InvokeTest(){}
	
	public InvokeTest(String str){
		ass = str;
	}

	public int add(int param1, int param2) {
		return param1 + param2;
	}

	public String echo(String mesg) {
		return ass + mesg;
	}
	
	

	public static void main(String[] args) throws Exception {
		Class<?> classType = Class.forName("tests.InvokeTest");//InvokeTest.class;
		System.out.println(classType.getName());
		
		Object instance = classType.newInstance();
		
		//must be public constructor
		Object  con = classType.getConstructor(String.class).newInstance("luangeng");
		
		Method addMethod = classType.getMethod("add", int.class, int.class );
		Object result = addMethod.invoke(instance,  100, 20 );
		System.out.println(result);

		Method echoMethod = classType.getMethod("echo",  String.class );
		result = echoMethod.invoke(instance, "hello" );
		System.out.println(result);
		
		
		Class<Integer> c = Integer.class;
		int var = c.getConstructor(int.class).newInstance(111);
        System.out.println(var);
	}

}
