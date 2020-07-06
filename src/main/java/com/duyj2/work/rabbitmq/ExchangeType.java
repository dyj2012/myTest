package com.duyj2.work.rabbitmq;

/**
 * Created by LG on 2017/8/18.
 */
public enum ExchangeType {

    DEFAULT,

    DIRECT,

    TOPIC,

    HEADERS,

    FANOUT;

    @Override
    public String toString()
    {
        return this.name().toLowerCase();
    }
}
