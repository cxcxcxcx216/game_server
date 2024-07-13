package com.game.data.redis;

import com.alibaba.fastjson.JSON;
import com.game.entity.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisDataCenter {

    public static final RedisDataCenter INSTANCE = new RedisDataCenter();

    public static RedisDataCenter getInstance(){
        return INSTANCE;
    }

    public Player getPlayer(long pid){
        String s = RedisFactory.commands.get(Long.toString(pid));
        if (s.isEmpty()) {
            log.info("pid = {}玩家在数据库中不存在！",pid);
            return null;
        }
        Player player = JSON.parseObject(s, Player.class);
        return player;
    }

    public void savePlayer(Player player){
        String s = JSON.toJSONString(player);
        RedisFactory.commands.set(Long.toString(player.getPid()),s);
    }


}
