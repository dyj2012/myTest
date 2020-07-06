package com.duyj2.work.spring.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LG on 2017/1/1.
 */
public class AopTest {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ServiceFlow ser = ctx.getBean(ServiceFlow.class);
        ser.serviceMethod("haha", 100);

        ser.exceptionmethod();

        ser.exception();
    }
}
