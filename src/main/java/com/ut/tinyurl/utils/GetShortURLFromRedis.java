package com.ut.tinyurl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class GetShortURLFromRedis {
    @Autowired
    StringRedisTemplate redisTemplate;

    public String getShortURLFromRedis(){
        String shortURL = redisTemplate.opsForValue().get("6");
//        String prefix = "www.ut.com/";
        redisTemplate.opsForValue().getOperations().delete("6");
        return shortURL;
}
}
