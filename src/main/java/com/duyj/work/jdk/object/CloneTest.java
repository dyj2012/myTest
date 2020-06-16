package com.duyj.work.jdk.object;

import com.duyj.work.utils.Q;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CloneTest implements Cloneable, Serializable {
    private int id;
    private String name;
    private Date birday;
    private List<String> list = new ArrayList<String>();
    private transient String pwd = "pwd";


    public CloneTest() {
        Q.p("constructor.");
    }

    public CloneTest(int id, String name, Date birday) {
        this();
        this.id = id;
        this.name = name;
        this.birday = birday;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest a = new CloneTest(11, "Jack", new Date());
        a.initList();
        CloneTest b = a.clone();
        CloneTest c = a.deepClone();
        a.clrList();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public void initList() {
        this.list.add("1");
        this.list.add("2");
        this.list.add("3");
    }

    public void clrList() {
        this.list.clear();
    }

    @Override
    public String toString() {
        return "[" + id + "-" + name + "-" + birday + "-" + pwd + "-" + list.toString() + "]";
    }

    // 重写clone方法可以对克隆过程做更多处理,返回类型可以为CloneTest(父类为Object)
    @Override
    public CloneTest clone() {
        CloneTest c = null;
        try {
            c = (CloneTest) super.clone();
            //引用对象需要独立拷贝
            c.birday = (Date) birday.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return c;
    }

    //利用序列化对象进行深度复制，克隆基本对象和引用对象(除了transient)
    public CloneTest deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(this);
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            CloneTest o = (CloneTest) is.readObject();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
