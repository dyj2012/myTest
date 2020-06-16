package com.duyj.work.patterns.prototype;

import java.io.*;
import java.util.Date;
import java.util.List;

//实现了克隆方法的原型类
public class PrototypeClass implements Cloneable,Serializable, Prototype{

    private static final long serialVersionUID = 1L;

    String name;

    int age;

    List list;

    Date time;

    PrototypeClass(String name, int age, List list){
        this.name = name;
        this.age = age;
        this.list = list;
        this.time = new Date();
    }

    public void show(){
        System.out.println(this.name + " " + this.age + " " + this.time + " " + this.list + " " + this.hashCode());
    }

    @Override
    public PrototypeClass cloneme(){
        PrototypeClass that = null;
        try {
            // 将对象写到流里
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            // 从流里读回来
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            that = (PrototypeClass) ois.readObject();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return that;
    }

}
