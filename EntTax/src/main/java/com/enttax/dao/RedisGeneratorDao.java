package com.enttax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by lcyanxi on 17-3-30.
 */
public abstract class RedisGeneratorDao<k extends Serializable, v extends Serializable> {
    @Autowired
    private RedisTemplate<k,v> redisTemplate;


    public void setRedisTemplate(RedisTemplate<k, v> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    public void test(){

    }
}
