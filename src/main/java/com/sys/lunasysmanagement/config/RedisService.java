package com.sys.lunasysmanagement.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 封装redis操作
 *
 * @author wangfangrui
 * @date 2019/8/21 17:04
 */
@Slf4j
@Component
public class RedisService<T> {

    private RedisTemplate<Serializable, byte[]> redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        this.redisTemplate = redisTemplate;
    }

    public boolean set(String key, T value) {
        try {
            ValueOperations<Serializable, byte[]> operations = redisTemplate.opsForValue();
            FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
            byte[] serialize = fastJsonRedisSerializer.serialize(value);
            operations.set(key, serialize);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }


    public boolean set(String key, T value, Long expireTime) {
        try {
            ValueOperations<Serializable, byte[]> operations = redisTemplate.opsForValue();
            FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
            byte[] serialize = fastJsonRedisSerializer.serialize(value);
            operations.set(key, serialize);
            return expire(key, expireTime.longValue());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }


    public void remove(String... keys) {
        for (int i = 0; i < keys.length; i++) {
            remove(keys[i]);
        }
    }


    public boolean remove(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }


    public boolean exists(String key) {
        return redisTemplate.hasKey(key).booleanValue();
    }


    public T get(String key) {
        try {
            ValueOperations<Serializable, byte[]> operations = redisTemplate.opsForValue();
            FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
            byte[] bytes = operations.get(key);
            return (T) fastJsonRedisSerializer.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }


    public boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }


    public long count(List list) {
        try {
            return redisTemplate.countExistingKeys(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return 0;
        }
    }

    public Set<Serializable> keys(String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            return new LinkedHashSet<>();
        }
    }

}

