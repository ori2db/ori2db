package com.c2uol.base.redis.aspect;

import java.lang.reflect.Method;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.c2uol.base.redis.RedisClient;
import com.c2uol.base.utils.RedisSourcePool;

import redis.clients.jedis.Jedis;

@Aspect
@Component
/**
 * 
 * @描述: redis 客户端运行切面
 * @作者: Lyon
 * @版本: v1.0
 * @时间: 2017年8月2日上午12:17:31
 *
 */
public class RedisClientExecuteAspect {

    private Logger logger = LogManager.getLogger(RedisClientExecuteAspect.class);

    @Resource
    RedisSourcePool redisSourcePool;

    @After("!execution(* com.c2uol.base.redis.RedisClient.conf(..)) and execution(* com.c2uol.base.redis.RedisClient.*(..))")
    /**
     * 
     * @描述: 执行完redis客户端方法以后，将连接对象放回连接池
     * @参数: @param point
     * @返回值: void
     * @版本: v1.0
     * @时间: 2017年8月2日上午2:11:00
     *
     */
    public void closedJedisConenction(JoinPoint point) {
        logger.info("redis 客户端开始关闭jedis连接通道...");
        RedisClient client = (RedisClient) point.getTarget();
        try {
            Method method = client.getClass().getDeclaredMethod("getJedis");
            method.setAccessible(true);
            Object result = method.invoke(client);
            if (result == null || !(result instanceof Jedis)) {
                return;
            }
            Jedis jedis = (Jedis) result;
            jedis.close();
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
