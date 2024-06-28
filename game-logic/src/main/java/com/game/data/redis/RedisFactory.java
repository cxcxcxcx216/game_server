package com.game.data.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RedisFactory {

    public static RedisCommands<String, String> commands;
    private static StatefulRedisConnection<String, String> connection;
    private static RedisClient redisClient;

    public static void init(){
        redisClient = RedisClient.create("redis://localhost");
        connection = redisClient.connect();
        commands = connection.sync();
        // 获取存储的数据并输出
        log.info("redis is running ");
    }

    public static void shoutDown(){
        // 关闭连接
        connection.close();
        redisClient.shutdown();
    }

}
