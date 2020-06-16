package com.duyj.work.patterns.strategy;

/**
 * Created by LG on 2017/3/12.
 */
public class StrategyTest{

    public static void main(String[] args){

        Context c1 = new Context(new Strategy1());
        c1.print();

        Context c2 = new Context(new Strategy2());
        c2.print();

        Context c3 = new Context(new Strategy3());
        c3.print();

    }
}
