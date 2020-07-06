package com.duyj2.work.patterns.strategy;

//环境(Context)角色,应用策略的类
public class Context{

    private String[] msg = {"abc", "123", "Tom", "smile", "asd"};

    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public void print(){
        this.strategy.printf(this.msg);
    }
}
