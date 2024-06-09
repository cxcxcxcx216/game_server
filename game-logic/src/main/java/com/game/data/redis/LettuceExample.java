package com.game.data.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceExample {
    public static void main(String[] args) {
        // 连接到 Redis 服务
        RedisClient redisClient = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();

        // 设置 Redis 字符串数据
        commands.set("key", "value");

        // 获取存储的数据并输出
        System.out.println("Stored string in Redis: " + commands.get("key"));

        // 关闭连接
        connection.close();
        redisClient.shutdown();
    }
}
