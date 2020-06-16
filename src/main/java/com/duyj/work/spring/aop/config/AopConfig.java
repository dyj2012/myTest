package com.duyj.work.spring.aop.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
    使用Config注入切面
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) //启动AOP注解驱动
@ComponentScan("com.duyj.work.spring.aop.config")
public class AopConfig {

    public static void main(String[] args) {

        //从Java配置类中加载应用上下文
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig.class);
        ServiceFlow ser = ctx.getBean(ServiceFlow.class);

        ser.serviceMethod("haha", 100);
    }

}
