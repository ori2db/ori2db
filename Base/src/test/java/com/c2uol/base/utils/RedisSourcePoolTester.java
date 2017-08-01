package com.c2uol.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.c2uol.base.redis.RedisClient;

import redis.clients.jedis.Jedis;

public class RedisSourcePoolTester {

    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    // @Test
    public void testerGetConnection() {
        RedisSourcePool redisSourcePool = applicationContext.getBean("redisSourcePool", RedisSourcePool.class);
        Jedis jedis = redisSourcePool.getResrouce("jedisPool_default");
    }

    @Test
    public void testerRedisClient() {
        RedisClient redisClient = applicationContext.getBean("redisClient", RedisClient.class);
        redisClient.conf("jedisPool_default", 0);
        Map<String, String> map = new HashMap<String, String>();
        map.put("001", "hello");
        String result = redisClient.hmset("tester002", map);
        System.out.println(result);
    }
}
