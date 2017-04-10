package com.enttax.config;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by lcyanxi on 17-3-30.
 */
public class RedisConnectTest {
    @Test
    public void redisConnectTest(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("username","lichang");
        jedis.set("password","1214521");
        jedis.set("address","重庆黔江");
        System.out.println(jedis.get("username"));
        System.out.println(jedis.keys("*"));

    }
}
