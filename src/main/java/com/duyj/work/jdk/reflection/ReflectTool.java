package com.duyj.work.jdk.reflection;

import com.duyj.work.utils.Q;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射工具类，用于分析一个类
 */
public class ReflectTool {

    public static <T> void all(T object) {
        listFiled(object);
        listMethod(object);

        try {
            printGet(object);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void listFiled(T object) {
        Q.p("---------------------Fields-----------------");
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Q.p(field.isAccessible() + " " + field.getType().getName() + " " + field.getName());
        }
        Q.p();
    }

    public static <T> void listMethod(T object) {
        Q.p("---------------------Methods-----------------");
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Q.p(method.getName() + " " + Arrays.toString(method.getParameters()));
        }
        Q.p();
    }

    //调用所有无参的get方法
    public static <T> void printGet(T object) throws InvocationTargetException, IllegalAccessException {
        Q.p("---------------------Gets-----------------");
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                method.setAccessible(true);
                Q.p(method.getName() + " : " + method.invoke(object));
            }
        }
        Q.p();
    }
}
