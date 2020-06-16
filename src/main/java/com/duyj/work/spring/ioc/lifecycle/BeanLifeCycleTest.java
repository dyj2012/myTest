package com.duyj.work.spring.ioc.lifecycle;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLifeCycleTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(BeanLifeCycleTest.class);
        TestBean bean = con.getBean(TestBean.class);
        bean.toString();

        con.close();
    }

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    public TestBean testBean() {
        return new TestBean();
    }

}