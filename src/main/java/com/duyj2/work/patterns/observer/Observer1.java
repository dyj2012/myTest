package com.duyj2.work.patterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者1
 */
public class Observer1 implements Observer{

    //观察到变化之后进行的行为
    @Override
    public void update(Observable o, Object arg){
        System.out.println(this.getClass().getSimpleName() + " 观察到 " + o.getClass().getSimpleName() + " changed: " + arg.toString());
    }
}
