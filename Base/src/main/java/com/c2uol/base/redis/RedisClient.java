package com.c2uol.base.redis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * @时间: 2017年8月16日 下午11:42:30
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
     * @描述: 返回 key 所关联的字符串值
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

    /**
     * 
     * @描述: 将字符串值 value 关联到 key
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param value
     * @参数: @param sec
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:44:15
     */
    public String set(String key, String value, int sec) {
        return this.set(key.getBytes(), value.getBytes(), sec);
    }

    /**
     * 
     * @描述: 写入一个redis键值对数据
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param value
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:44:53
     */
    public String set(String key, String value) {
        return this.set(key.getBytes(), value.getBytes());
    }

    /**
     * 
     * @描述: 将字符串值 value 关联到 key
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param value
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:45:20
     */
    public String set(byte[] key, byte[] value) {
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 删除给定的一个或多个 key
     * @作者: Lyon
     * @参数: @param key
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:45:30
     */
    public Long del(String... keys) {
        try {
            return jedis.del(keys);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 检查给定 key 是否存在
     * @作者: Lyon
     * @参数: @param key
     * @参数: @return
     * @返回值: Boolean
     * @时间: 2017年8月16日 下午11:45:50
     */
    public Boolean exsts(String key) {
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 
     * @描述: 查找所有符合给定模式 pattern 的 key
     * @作者: Lyon
     * @参数: @param pattern
     * @参数: @return
     * @返回值: List<String>
     * @时间: 2017年8月16日 下午11:46:03
     */
    public List<String> keys(String pattern) {
        List<String> arr = new ArrayList<String>();
        try {
            Set<String> result = jedis.keys(pattern);
            if (result != null && result.size() > 0) {
                arr.addAll(result);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return arr;
    }

    /**
     * 
     * @描述: 设置一个redis键值对对象，并设置生命周期
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param value
     * @参数: @param sec
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:46:21
     */
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

    /**
     * 
     * @描述: 同时将多个 field-value (域-值)对设置到哈希表 key 中
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param params
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:46:55
     */
    public String hmset(String key, Map<String, String> params) {
        try {
            return jedis.hmset(key, params);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 将哈希表 key 中的域 field 的值设为 value
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param field
     * @参数: @param value
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:47:13
     */
    public Long hset(String key, String field, String value) {
        try {
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 将一个或多个值 value 插入到列表 key 的表头
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param strings
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:47:39
     */
    public Long lpush(String key, String... strings) {
        try {
            return jedis.lpush(key, strings);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 将一个或多个值 value 插入到列表 key 的表尾(最右边)
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param strings
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:48:20
     */
    public Long rpush(String key, String... strings) {
        try {
            return jedis.rpush(key, strings);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 移除并返回列表 key 的尾元素
     * @作者: Lyon
     * @参数: @param key
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:48:42
     */
    public String rpop(String key) {
        try {
            return jedis.rpop(key);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 移除并返回列表 key 的头元素
     * @作者: Lyon
     * @参数: @param key
     * @参数: @return
     * @返回值: String
     * @时间: 2017年8月16日 下午11:49:02
     */
    public String lpop(String key) {
        try {
            return jedis.rpop(key);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param start
     * @参数: @param end
     * @参数: @return
     * @返回值: List<String>
     * @时间: 2017年8月16日 下午11:51:47
     */
    public List<String> lrange(String key, long start, long end) {
        try {
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 添加一个有序集合
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param score
     * @参数: @param member
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:50:09
     */
    public Long zadd(String key, double score, String member) {
        try {
            return jedis.zadd(key, score, member);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @描述: 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
     * @作者: Lyon
     * @参数: @param key
     * @参数: @param members
     * @参数: @return
     * @返回值: Long
     * @时间: 2017年8月16日 下午11:57:48
     */
    public Long zrem(String key, String... members) {
        try {
            return jedis.zrem(key, members);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }
    
    
}
