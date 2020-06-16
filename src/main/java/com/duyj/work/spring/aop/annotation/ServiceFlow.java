package com.duyj.work.spring.aop.annotation;

import org.springframework.stereotype.Service;

/**
 * Created by LG on 2017/1/1.
 */
@Service
public class ServiceFlow {

    public String serviceMethod(String a, int b) {
        System.out.println("ServiceMethod invoked");
        return a + b;
    }

    public void exceptionmethod() {
        int a = 10 / 0;
    }

    public void exception() throws NullPointerException {
        throw new NullPointerException();
    }

}
