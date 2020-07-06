package com.duyj2.work.patterns.proxy;

//被代理者
public class Client implements IClient {

	@Override
	public void doSomething(String thing) {
		System.out.println("do a thing:" + thing);
	}
}
