package com.duyj2.work.patterns.singleton;

public class SingletonClass{

    //内部产生该类唯一的实例，并对外界不可见
    private static final SingletonClass MyInstance = new SingletonClass();

    //私有的构造函数，防止被外界实例化
    private SingletonClass(){
    }

    //返回唯一实例的静态方法，供外界调用
    public static SingletonClass getInstance(){
        return MyInstance;
    }

}
