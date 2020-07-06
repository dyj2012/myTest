package com.duyj2.work.cglib;

import com.duyj2.work.utils.Q;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*
 * 动态代理类
 */
public class ClientProxy implements MethodInterceptor {

    // 被代理对象
    private Object targetObject;

    ClientProxy(Client client) {
        this.targetObject = client;
    }

    // 动态生成一个新的类，使用父类的无参构造方法创建一个指定了特定回调的代理实例
    public Object getProxyObject() {
        //动态代码生成器
        Enhancer enhancer = new Enhancer();
        //回调方法
        enhancer.setCallback(this);
        //设置生成类的父类类型
        enhancer.setSuperclass(targetObject.getClass());
        //动态生成字节码并返回代理对象
        return enhancer.create();
    }

    // 实现了一个方法拦截器接口
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("hashCode")) {
            return methodProxy.invoke(targetObject, args);
        }

        Q.p(args);
        System.out.println("方法调用前织入的横切内容,method:" + method.getName());

        // 调用方法
        Object result = methodProxy.invoke(targetObject, args);

        System.out.println("方法调用后织入的横切内容,result:" + result + "\n");

        return result;
    }

}
