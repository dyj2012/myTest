package com.duyj2.work.patterns.observer;

import java.util.Observable;
import java.util.Observer;

/*
 * 被观察者1
 */
public class Watched1 extends Observable{

    private String data;

    public String getData(){
        return data;
    }

    public void setDate(String str){
        this.data = str;
        setChanged();
        notifyObservers(str);
    }

}

