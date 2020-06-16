package com.duyj.work.spring.ioc.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.duyj.work.spring.ioc.config")
public class BeanScanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(BeanScanTest.class);
        TestService bean = con.getBean(TestService.class);
        bean.test();
    }

}