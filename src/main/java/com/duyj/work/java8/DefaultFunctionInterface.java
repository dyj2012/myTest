package com.duyj.work.java8;

import com.duyj.work.utils.Q;

/*
 * 所有实现这个接口的类都会接受默认方法的实现，除非子类提供的自己的实现
 * */
public interface DefaultFunctionInterface {

    default void invoke() {
        Q.p("default function");
    }

    class DefaultFunctionInterfaceTest implements DefaultFunctionInterface {

        public static void main(String[] args) {
            DefaultFunctionInterfaceTest test = new DefaultFunctionInterfaceTest();
            test.invoke();
        }
    }

}


