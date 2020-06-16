package com.duyj.work.patterns.observer;

import com.duyj.work.utils.Q;

/**
 * 测试类
 * 观察者模式（Observer Pattern）:定义对象之间的一对多的依赖关系，使得每当一个对象状态发生改变时，其相关依赖对象皆得到通知并被自动更新。
 * 别名有：发布-订阅（Publish/Subscribe）模式、模型-视图（Model/View）模式、源-监听器（Source/Listener）模式、从属者（Dependents）模式。
 */
public class ObserverTest{

    public static void main(String[] args){

        Observer1 obs1 = new Observer1();
        Observer2 obs2 = new Observer2();

        Watched1 w1 = new Watched1();
        w1.addObserver(obs1);  //添加观察者
        w1.addObserver(obs2);

        Watched2 w2 = new Watched2();
        w2.addObserver(obs1);
        w2.addObserver(obs2);

        w1.setDate("data 1 changed!");   //被观察者1变化

        w2.complete();   //被观察者2变化

        //观察者数量
        Q.p(w1.countObservers());

    }
}
