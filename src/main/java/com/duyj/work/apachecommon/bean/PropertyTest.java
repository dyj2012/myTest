package com.duyj.work.apachecommon.bean;

import com.duyj.work.Person;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/*
    访问Bean属性
 */
public class PropertyTest {

    public static void main(String[] args) {
        Person person = new Person(1);
        person.setAge(100);
        person.setName("la");
        person.setBirthday(new Date());

        String name;
        try {
            name = (String) PropertyUtils.getSimpleProperty(person, "name");
            PropertyUtils.setSimpleProperty(person, "name", name + "123");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(person);


    }
}
