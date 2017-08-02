package com.c2uol.base.redis;

import java.util.Map;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.c2uol.base.redis.exception.RedisClientException;
import com.c2uol.base.utils.RedisSourcePool;

import redis.clients.jedis.Jedis;

/**
 * 
 * @描述: redis 客户端
 * @作者: Lyon
 * @版本: v1.0
 * @时间: 2017年7月28日上午2:23:52
 *
 */
@Repository
@Scope(value = "prototype")
public class RedisClient {

    private Logger logger = LogManager.getLogger(RedisClient.class);

    @Resource
    RedisSourcePool redisSourcePool;

    public RedisClient() {

    }

    /**
     * 
     * @描述: 配置使用的库以及使用的节点
     * @参数: @param db_name
     * @参数: @param node
     * @返回值: void
     * @版本: v1.0
     * @时间: 2017年7月29日上午12:41:50
     *
     */
    public void conf(String db_name, int node) {
        if (db_name == null) {
            new RedisClientException("database name not is null.");
        }
        jedis = redisSourcePool.getResrouce(db_name);
        if (node > 0) {
            jedis.select(node);
        }
    }

    private Jedis jedis = null;

    @SuppressWarnings(value = "all")
    private Jedis getJedis() {
        return jedis;
    }

    /**
     * 
     * @描述: redis get
     * @参数: @param key
     * @参数: @return
     * @返回值: String
     * @版本: v1.0
     * @时间: 2017年7月28日上午2:35:05
     *
     */
    public String get(String key) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public String set(String key, String value, int sec) {
        return this.set(key.getBytes(), value.getBytes(), sec);
    }

    public String set(String key, String value) {
        return this.set(key.getBytes(), value.getBytes());
    }

    public String set(byte[] key, byte[] value) {
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public String set(byte[] key, byte[] value, int sec) {
        try {
            String result = jedis.set(key, value);
            jedis.expire(key, sec);
            return result;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public String hmset(String key, Map<String, String> params) {
        try {
            return jedis.hmset(key, params);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }
}
