package com.duyj.work.spring.redis;

import com.duyj.work.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTest {

    @Autowired
    private StringRedisTemplate strTemplate;

    @Autowired
    private RedisTemplate<String, Person> template;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(DataRedisConfig.class);
        RedisTest test = con.getBean(RedisTest.class);
        test.test();
    }

    public void test() {
        Person p = new Person(1);
        template.opsForValue().set("p1", p);
        System.out.println(template.opsForValue().get("p1"));

        strTemplate.opsForValue().set("name", "luangeng");
        System.out.println(strTemplate.opsForValue().get("name"));
    }

}
