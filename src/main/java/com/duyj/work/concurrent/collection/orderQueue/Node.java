package com.duyj.work.concurrent.collection.orderQueue;

public class Node {

    private int index;

    private String str;

    public Node(int index, String str) {
        this.index = index;
        this.str = str;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", str='" + str + '\'' +
                '}';
    }
}
