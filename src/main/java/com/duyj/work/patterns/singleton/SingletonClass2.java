package com.duyj.work.patterns.singleton;

/**
 * Created by LG on 2017/3/12.
 */
public class SingletonClass2{

    //私有的构造函数，防止被外界实例化
    private SingletonClass2(){
    }

    //静态内部类执行一次，保证线程安全
    private static class InstanceHolder{
        private static final SingletonClass2 MyInstance = new SingletonClass2();
    }

    //返回唯一实例的静态方法，供外界调用
    public static SingletonClass2 getInstance(){
        return InstanceHolder.MyInstance;
    }
}
