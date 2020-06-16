package com.duyj.work.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class JedisPoolTest {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, "localhost");
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void closePool() {
        if (pool != null)
            pool.close();
    }

    public static void main(String[] args) {
        /// Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = pool.getResource()) {
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        }
    }

    public void example() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } finally {
            // You have to close jedis object. If you don't close then
            // it doesn't release back to pool and you can't get a new
            // resource from pool.
            if (jedis != null) {
                jedis.close();
            }
        }
    }


}
