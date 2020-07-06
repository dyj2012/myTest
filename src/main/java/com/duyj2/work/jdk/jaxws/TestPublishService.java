package com.duyj2.work.jdk.jaxws;

import javax.xml.ws.Endpoint;

/**
 * http://localhost:9999/ws?wsdl
 */
public class TestPublishService {

    public static void main(String[] args) {
        System.out.println("发布Service");
        /**
         * 两个参数
         * 参数1：对外访问地址
         * 参数2：发布的Service实现类
         * 地址中的端口号自己定义，只要不冲突即可
         */
        Endpoint.publish("http://localhost:9999/ws", new WeatherServiceImpl());
    }


}
