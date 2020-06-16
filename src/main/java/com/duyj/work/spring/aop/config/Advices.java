package com.duyj.work.spring.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by LG on 2017/1/1.
 * 切面 包含切点和通知    切点：插入的位置，通知：插入执行的动作
 */
@Aspect
@Component
public class Advices {

    //使用pointcut注解声明频繁使用的切点表达式
    @Pointcut("execution(* com.duyj.work.spring.aop.config.ServiceFlow.*(..))")
    public void flow() {
    }

    //通知
    @Before("flow()")//切点
    public void before(JoinPoint jp) {
        System.out.println("----------前置通知----------method:" + jp.getSignature().getName());
    }

    //通知
    @After("flow()")
    public void after(JoinPoint jp) {
        System.out.println("----------结束后异常后通知----------method:" + jp.getSignature().getName());
    }

    @AfterReturning("flow()")
    public void afterReturn(JoinPoint jp) {
        System.out.println("----------返回后通知----------method:" + jp.getSignature().getName());
    }

    //使用pointcut声明的切点
    @AfterThrowing("flow()")
    public void afterThrow(JoinPoint jp) {
        System.out.println("----------异常通知----------method:" + jp.getSignature().getName());
    }


//    @Around("flow()")
//    public void around(ProceedingJoinPoint jp) {
//        System.out.println("----------围绕通知 before----------method:" + jp.getSignature().getName());
//        try {
//            jp.proceed();//通知方法中调用被通知的方法
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("----------围绕通知 after----------method:" + jp.getSignature().getName());
//    }
}
