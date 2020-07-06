package com.duyj2.work.redis;

import com.duyj2.work.utils.Q;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.*;

public class JedisTest {

    private Jedis jedis = null;

    JedisTest() {
        //连接redis服务
        //jedis = new Jedis("127.0.0.1", 6379);
        jedis = JedisPoolTest.getJedis();
        //jedis = new Jedis("localhost");
        //权限认证
        //jedis.auth("admin");
        //查看服务是否运行
        System.out.println("Server status: " + jedis.ping());
    }

    public static void main(String[] args) throws InterruptedException {
        JedisTest rtest = new JedisTest();
        rtest.testString();
        rtest.testList();
        rtest.testSet();
        rtest.testMap();
        rtest.flushDB();
    }

    public void testString() {
        //添加
        jedis.set("name", "luangeng");
        //拼接
        jedis.append("name", " is good");
        Q.p(jedis.get("name"));

        //删除某个键
        jedis.del("name");
        //设置多个键值对
        jedis.mset("name", "luangeng", "age", "123", "qq", "1234");
        //增1
        jedis.incr("age");
        Q.p(jedis.get("age"));
    }

    public void testList() {
        //Remove the specified keys. returns the number of keys removed.
        jedis.del("list", "key2");
        // 从左边加入
        jedis.lpush("list", "5");
        // 从右边加入
        jedis.rpush("list", "0");
        jedis.lpush("list", "-1");
        jedis.rpush("list", "2");
        long n = jedis.llen("list");
        //Sort a Set or a List and Store the Result at dstkey.
        jedis.sort("list", "b");
        System.out.println(jedis.lrange("list", 0, -1));
        System.out.println(jedis.lrange("b", 0, -1));
    }

    public void testSet() {
        jedis.del("set");
        //添加
        jedis.sadd("set", "123");
        jedis.sadd("set", "123");
        jedis.sadd("set", "1");
        jedis.sadd("set", "1");
        jedis.sadd("set", "2");
        //移除
        jedis.srem("set", "2");

        System.out.println(jedis.scard("set"));
        System.out.println(jedis.smembers("set"));
        System.out.println(jedis.sismember("set", "123"));
    }

    public void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        jedis.hmset("map", map);

        List<String> rsmap = jedis.hmget("map", "k1", "k2", "k3");
        System.out.println(rsmap);

        jedis.hdel("k3", "v3");

        Iterator<String> iter = jedis.hkeys("map").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("map", key));
        }
        jedis.hlen("map");
        jedis.exists("map");
        jedis.hkeys("map");
        jedis.hvals("map");
    }


    public void transaction() {
        jedis.watch("key1", "key2");
        Transaction t = jedis.multi();
        t.set("foo", "bar");
        t.exec();

        t.set("fool", "bar");
        Response<String> result1 = t.get("fool");

        t.zadd("foo", 1, "barowitch");
        t.zadd("foo", 0, "barinsky");
        t.zadd("foo", 0, "barikoviev");
        Response<Set<String>> sose = t.zrange("foo", 0, -1);   // get the entire sortedset
        t.exec();                                              // dont forget it

        String foolbar = result1.get();                       // use Response.get() to retrieve things from a Response
        int soseSize = sose.get().size();
    }

    ///send multiple commands at once, saving on round trip time
    public void pipelining() {
        Pipeline p = jedis.pipelined();
        p.set("fool", "bar");
        p.zadd("foo", 1, "barowitch");
        p.zadd("foo", 0, "barinsky");
        p.zadd("foo", 0, "barikoviev");
        Response<String> pipeString = p.get("fool");
        Response<Set<String>> sose = p.zrange("foo", 0, -1);
        p.sync();

        int soseSize = sose.get().size();
        Set<String> setBack = sose.get();
    }

    public void flushDB() {
        //Delete all the keys of the currently selected DB. This command never fails.
        jedis.flushDB();
        //Delete all the keys of all the existing databases.This command never fails.
        //jedis.flushAll();
    }

}
