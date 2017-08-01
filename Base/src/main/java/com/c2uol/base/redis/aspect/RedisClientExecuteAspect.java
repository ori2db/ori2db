package com.c2uol.base.redis.aspect;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.c2uol.base.redis.RedisClient;
import com.c2uol.base.utils.RedisSourcePool;

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

    @Before("execution(* com.c2uol.base.redis.RedisClient.conf(..))")
    public void before(JoinPoint point) {
        logger.info("redis 客户端切面开始...");
        Object[] args = point.getArgs();
        String db_name = null;
        Integer node = 0;
        if (args[0] instanceof String) {
            db_name = String.valueOf(args[0]);
        }
        if (args[1] instanceof Integer) {
            node = Integer.parseInt(args[1].toString());
        }
        RedisClient client = (RedisClient)point.getTarget();
        client.conf(db_name, node);
    }
}
