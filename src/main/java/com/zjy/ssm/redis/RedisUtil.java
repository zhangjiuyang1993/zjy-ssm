package com.zjy.ssm.redis;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Component
public class RedisUtil {
    private static final String CACHE_NAME = "zjy-ssm-cache";

    private static final int EXPIRE_TIME = 3000;

    private RedisTemplate redisTemplate;

    private RedisCache redisCache;

    public void init() {
        redisTemplate = SpringUtil.getBean("redisTemplate");
        redisCache = new RedisCache(CACHE_NAME, CACHE_NAME.getBytes(), redisTemplate, EXPIRE_TIME);
    }

    public void put(String key, Object obj) {
        redisCache.put(key, obj);
    }

    public Object get(String key, Class clazz) {
        return redisCache.get(key) == null ? null : redisCache.get(key, clazz);
    }

    public void del(String key) {
        redisCache.evict(key);
    }
}
