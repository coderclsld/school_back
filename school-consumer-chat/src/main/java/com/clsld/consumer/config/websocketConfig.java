package com.clsld.consumer.config;

import com.clsld.consumer.utils.RedisUtil;
import com.clsld.consumer.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
public class websocketConfig {

    @Resource
    public void setRedisUtil (RedisUtil redisUtil){
        WebSocketHandler.redisUtil = redisUtil;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        WebSocketHandler.redisTemplate = redisTemplate;
    }
}
