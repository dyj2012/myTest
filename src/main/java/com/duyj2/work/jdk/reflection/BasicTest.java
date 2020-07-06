package com.duyj2.work.jdk.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.duyj2.work.utils.Q;

public class BasicTest {

	public static void main(String[] args) throws Exception {

		Demo demo = new Demo(10,"moss");

		// 所有类的对象都是Class的实例
		Class<?> clazz = null;
		Class<?> clazz2 = null;

		//获取类对象
		clazz = Class.forName("demos.reflection.Demo");
		clazz2 = Demo.class;
		clazz2 = demo.getClass();
		Q.p(clazz.getClass());
		Q.p(clazz==clazz2);

		// 使用默认构造函数 创建一个新的实例  
		demo = (Demo) clazz.newInstance();
		demo.me();
		
		//获取所有public的构造函数
		Constructor<?>[] con =clazz2.getConstructors(); 
		Q.p(con);
		
		//使用自定义构造函数 创建一个新的实例  
		demo=(Demo) con[1].newInstance(100,"jack");
		demo.me();
		
		//获取超类 接口
		Q.p(clazz.getSuperclass());
		Q.p(clazz.getInterfaces());
		
		//获取所有属性，不包括继承的
		Field[] fields = clazz.getDeclaredFields();
		Q.p(fields);
		
		//获取无参函数，调用无参函数
		Method method=clazz.getMethod("me");
		Q.p("method "+method);
		method.invoke(clazz.newInstance());
		
		//获取有参函数，调用有参函数 
		method = clazz.getDeclaredMethod("you", int.class,String.class);
		Q.p("method2 "+method);
		method.invoke(clazz.newInstance(), 19, "you");
		
		//获取所有方法,不包括父类的， 可通过getMethods()获取全部的
		Method[] methods = clazz.getDeclaredMethods();
		Q.p(methods);
		
		//给属性赋值
		Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(demo, "reSetName");
        Q.p(demo.getName());
        
        //获取注解
        method = clazz.getMethod("toString");
        Annotation[] as = method.getDeclaredAnnotations();
        Q.p(as);
	}
}
