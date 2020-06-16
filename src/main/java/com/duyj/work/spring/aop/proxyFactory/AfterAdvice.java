package com.duyj.work.spring.aop.proxyFactory;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by LG on 2017/1/1.
 */
public class AfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("-----------------后置通知AfterAdvice-----------------");
    }
}
