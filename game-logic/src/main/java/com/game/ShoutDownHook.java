package com.game;


import com.game.data.redis.RedisFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoutDownHook {

    public void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // 钩子函数逻辑
                log.info("服务器正在关闭...");
                // 可以在这里执行资源清理或其他必要的最终操作
                RedisFactory.shoutDown();//关闭redis数据库链接

            }
        });
    }
}
