package com.duyj.work.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理
public class ProxyAgent implements InvocationHandler {
	private Object client;
	//注入委托人
	public ProxyAgent(Object client) {
		this.client = client;
	}

	public static void main(String[] args) {
		//被代理者
		Client client = new Client();
		//代理者
		IClient proxy = (IClient) new ProxyAgent(client).getProxy();
		//调用代理动作
		proxy.doSomething("buy apple");
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 转调具体目标对象的方法
		Object object = method.invoke(client, args);
		return object;
	}

	//获取代理实例
	public Object getProxy(){
        return Proxy.newProxyInstance(getClass().getClassLoader(), client.getClass().getInterfaces(), this);
    }
}
