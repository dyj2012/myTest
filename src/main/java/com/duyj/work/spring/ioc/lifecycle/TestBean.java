package com.duyj.work.spring.ioc.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TestBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    //0.实例化对象

    //
    @Override
    public void setBeanName(String s) {
        System.out.println("1. BeanNameAware setBeanName");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("2. BeanFactoryAware setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("3. ApplicationContextAware setApplicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("4. BeanPostProcessor postProcessBeforeInitialization");
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5. InitializingBean afterPropertiesSet");
    }

    public void myInit() {
        System.out.println("6. my init method");
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("7. BeanPostProcessor postProcessAfterInitialization");
        return null;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("8. DisposableBean destroy");
    }

    public void myDestroy() {
        System.out.println("9. my destroy method");
    }
}
