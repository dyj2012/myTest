package com.duyj2.work.patterns.templatemethod;

/**
 * Created by LG on 2017/3/15.
 */
public abstract class AbstractTemplate implements ITemplate{

    // 某算法需要以下5个步骤，顺序必须如下,步骤3可省略
    @Override
    public final void method()
    {
        process_1();
        process_2();
        process_3();
        process_4();
        process_5();
    }

    // 父类私有
    private void process_1(){
        System.out.println("步骤1完成...");
    }

    // 必须子类实现
    protected abstract void process_2();

    // 子类选择性覆盖
    protected void process_3(){    }

    // 子类选择性覆盖
    protected void process_4(){
        System.out.println("步骤4完成...");
    }

    // 子类不可覆盖
    protected final void process_5(){
        System.out.println("步骤5完成...");
    }
}
