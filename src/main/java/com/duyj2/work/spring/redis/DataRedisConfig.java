package com.duyj2.work.spring.redis;

import com.duyj2.work.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan("com.duyj2.work.spring.redis")
public class DataRedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig con = new JedisPoolConfig();
        JedisConnectionFactory cf = new JedisConnectionFactory(con);
        return cf;
    }

    @Bean
    public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(cf);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf) {
        return new StringRedisTemplate(cf);
    }

}
