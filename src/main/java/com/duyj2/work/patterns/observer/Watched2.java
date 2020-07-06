package com.duyj2.work.patterns.observer;

import java.util.Observable;

/**
 * 被观察者2
 */
public class Watched2 extends Observable{

    private boolean complete = false;

    public void complete(){
        this.complete = true;
        setChanged();
        notifyObservers("task complete!");
    }
}
