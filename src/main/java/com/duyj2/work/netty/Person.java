package com.duyj2.work.netty;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by LG on 2017/11/22.
 */
@Message
public class Person implements Serializable {

    private int age;

    private String name;

    private boolean man;

    private List<String> list;

    private Date birth;

    private Person son;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Person getSon() {
        return son;
    }

    public void setSon(Person son) {
        this.son = son;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", man=" + man +
                ", list=" + list +
                ", birth=" + birth +
                ", son=" + son +
                '}';
    }
}
