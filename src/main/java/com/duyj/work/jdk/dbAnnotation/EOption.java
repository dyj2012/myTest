package com.duyj.work.jdk.dbAnnotation;

//数据库字段属性枚举
public enum EOption {
	//可空
	NONE,
	
	//非空
	NOT_NULL,
	
	PRIMARY,
	
	UNIQUE;
	
	public boolean withIn(EOption[] options){
		for(EOption o : options){
			if(o==this){
				return true;
			}
		}
		return false;
	}

}
