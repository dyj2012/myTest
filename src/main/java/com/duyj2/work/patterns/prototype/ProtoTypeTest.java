package com.duyj2.work.patterns.prototype;
/*
原型模式的主要思想是基于现有的对象克隆一个新的对象出来，一般是有对象的内部提供克隆的方法，通过该方法返回一个对象的副本。
这种创建对象的方式，相比其他创建型模式不同，
之前的讲述的工厂模式与抽象工厂都是通过工厂封装具体的new操作的过程，返回一个新的对象.
*/

import java.util.ArrayList;
import java.util.List;

//测试类
public class ProtoTypeTest{

    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("abc");

        PrototypeClass pc = new PrototypeClass("name1", 10, list);
        pc.show();

        PrototypeClass clone = (PrototypeClass) pc.cloneme();
        clone.show();

    }

}
