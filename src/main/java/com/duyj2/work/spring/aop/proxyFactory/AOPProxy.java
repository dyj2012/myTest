package com.duyj2.work.spring.aop.proxyFactory;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by LG on 2017/1/1.
 */
public class AOPProxy {

    public static Object getProxy(Object object) {
        //实例化Spring代理工厂
        ProxyFactory factory = new ProxyFactory();
        //设置被代理的对象
        factory.setTarget(object);
        //添加通知，横切逻辑
        factory.addAdvice(new BeforeAdvice());
        factory.addAdvice(new AfterAdvice());
        factory.addAdvice(new SurroundAdvice());
        return factory.getProxy();
    }

    public static void main(String[] args) {
        //从代理工厂中获得代理对象
        Client c = (Client) AOPProxy.getProxy(new Client());
        c.action1("222");
        c.action2(1, 2);
    }

}
