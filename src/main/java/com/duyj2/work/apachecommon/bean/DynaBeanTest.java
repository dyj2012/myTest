package com.duyj2.work.apachecommon.bean;

import com.duyj2.work.Person;
import org.apache.commons.beanutils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
动态构建Java Bean
 */
public class DynaBeanTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        //动态生成Bean类型
        DynaProperty[] props = new DynaProperty[]
                {
                        new DynaProperty("firstName", String.class),
                        new DynaProperty("lastName", String.class),
                        new DynaProperty("person", Person.class),
                        new DynaProperty("map", Map.class),
                };
        BasicDynaClass dynaClass = new BasicDynaClass("employee", null, props);

        DynaBean bean = dynaClass.newInstance();
        bean.set("firstName", "Fred");
        bean.set("lastName", "Flintstone");
        bean.set("person", new Person("name", 199));
        bean.set("map", new HashMap<String, Object>());

        System.out.println(bean.get("firstName"));
        System.out.println(bean.get("person"));


        //使用WrapDynaBean，我们可以将普通的javabean包装成DynaBean，并非常简便的使用DynaBean提供的API方法来访问bean成员属性
        Person e = new Person();
        e.setName("hello");
        DynaBean wrapper = new WrapDynaBean(e);
        String name = (String) wrapper.get("name");
        System.out.println("2" + name);


        ///自动构建Bean，可以包含Map List
        DynaBean dynaBean = new LazyDynaBean();
        dynaBean.set("foo", "bar");                   // simple
        dynaBean.set("customer", "title", "Mr");      // mapped
        dynaBean.set("customer", "surname", "Smith"); // mapped
        dynaBean.set("persons", 0, new Person(0));     // listed
        dynaBean.set("persons", 1, new Person(1));     // listed
        dynaBean.set("persons", 2, new Person(2));     // listed
        System.out.println(dynaBean.get("foo"));
        System.out.println(dynaBean.get("customer", "title"));
        System.out.println(dynaBean.get("persons", 1));
        Map<String, Object> myMap = ((LazyDynaBean) dynaBean).getMap();
        System.out.println(myMap);


        //控制某个bean属性的数据类型
        MutableDynaClass dynaClass2 = new LazyDynaClass();    // create DynaClass
        dynaClass2.add("amount", Integer.class);    // add property
        dynaClass2.add("users", Person[].class);          // add indexed property
        dynaClass2.add("orders", TreeMap.class);   // add mapped property
        DynaBean dynaBean2 = new LazyDynaBean(dynaClass2);
        dynaBean2.set("amount_", "s");
        //dynaBean2.set("amount", "s");//报错，需要为整型
        //dynaBean2.set("users", 1);//报错，需要维数组
        System.out.println(dynaBean.get("amount"));

    }

}
