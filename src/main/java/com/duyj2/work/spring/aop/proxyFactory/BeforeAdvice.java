package com.duyj2.work.spring.aop.proxyFactory;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by LG on 2017/1/1.
 */
public class BeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("-----------------前置通知BeforeAdvice-----------------");
    }
}
