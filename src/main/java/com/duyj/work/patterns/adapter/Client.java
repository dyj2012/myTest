package com.duyj.work.patterns.adapter;

/**
 * Created by LG on 2017/3/9.
 */
public class Client{

    //客户端调用
    public static void main(String[] args){

        // 客户端通过Target来调用
        Target target = new Adapter();
        target.targetMethod1();
        target.targetMethod2();
    }

}
