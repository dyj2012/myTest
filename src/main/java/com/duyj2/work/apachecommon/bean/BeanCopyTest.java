package com.duyj2.work.apachecommon.bean;

import com.duyj2.work.Empty;
import com.duyj2.work.Person;
import com.duyj2.work.utils.Q;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
把Map里的键值对值拷贝到bean的属性值中
拷贝一个bean的属性到另外一个bean中
 */
public class BeanCopyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "001");
        //map.put("address", "hz");
        map.put("id", 2);
        map.put("age", 100);
        map.put("birthday", new Date());
        map.put("empty", new Empty());


        //把Map里的键值对值拷贝到bean的属性值中
        Person person = new Person();
        BeanUtils.populate(person, map);
        System.out.println(person);

        //拷贝一个bean的属性到另外一个bean中，浅拷贝
        Person person2 = new Person();
        BeanUtils.copyProperties(person2, person);
        System.out.println(person2);
        System.out.println(person2.getEmpty() == person.getEmpty());

        //克隆, 浅拷贝
        Person person3 = (Person) BeanUtils.cloneBean(person);
        System.out.println(person3);
        System.out.println(person3.getEmpty() == person.getEmpty());

        //Bean转为Map
        Map map2 = BeanUtils.describe(person3);
        Q.p(map2);


    }

}
