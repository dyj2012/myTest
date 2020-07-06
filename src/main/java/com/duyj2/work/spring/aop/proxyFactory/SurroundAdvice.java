package com.duyj2.work.spring.aop.proxyFactory;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by LG on 2017/1/1.
 */
public class SurroundAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation m) throws Throwable{
        //前置横切面
        System.out.println("前置横切面，方法：" + m.getMethod() + " 被调用对象" + m.getThis() + " 参数 " + m.getArguments());
        //方法调用
        Object ret = m.proceed();
        //后置横切面
        System.out.println("后置横切面，返回值：" + ret);
        return ret;
    }
}
