package com.duyj.work.spring.ioc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestDao dao;

    public void test() {
        System.out.println("TestService test");
        dao.test();
    }
}
