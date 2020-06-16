package com.duyj.work.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LG on 2017/1/1.
 */
public class AopTestXml {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-xml.xml");
        ServiceFlow ser = ctx.getBean(ServiceFlow.class);
        ser.serviceMethod("haha", 100);

        ser.exceptionmethod();

        ser.exception();
    }
}
