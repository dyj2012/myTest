package com.duyj2.work.cglib;

public class CGlibProxyTest {

    //测试
    public static void main(String[] args) {
        Client client = new Client();

        ClientProxy proxy = new ClientProxy(client);

        Client proxy1 = (Client) proxy.getProxyObject();
        Client proxy2 = (Client) proxy.getProxyObject();

        System.out.println(client.hashCode());
        System.out.println(proxy1.hashCode());
        System.out.println(proxy2.hashCode());

        proxy1.hello("cglib");
        proxy2.add(10, 100);
    }

}