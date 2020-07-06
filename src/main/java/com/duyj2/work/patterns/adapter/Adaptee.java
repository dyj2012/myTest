package com.duyj2.work.patterns.adapter;

//需要适配的类
public class Adaptee {
	
	public void localMethod1(){
	    System.out.println("需要适配的类中的方法1");
	}

    public void localMethod2(){
        System.out.println("需要适配的类中的方法2");
    }

}
