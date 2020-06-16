package com.duyj.work.jdk.dbAnnotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DColumn {
	
	//字段类型
	EType type() default EType.VARCHAR;
	
	//类型相关值
	int value() default 10;
	
	//字段名称
	String name() default "";
	
	//字段属性
	EOption[] option() default EOption.NOT_NULL;

}
