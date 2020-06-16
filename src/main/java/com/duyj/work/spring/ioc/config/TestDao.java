package com.duyj.work.spring.ioc.config;

import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
    public void test() {
        System.out.println("TestDao test");
    }
}
