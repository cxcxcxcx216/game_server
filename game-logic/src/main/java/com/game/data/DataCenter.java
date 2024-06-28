package com.game.data;

import com.alibaba.fastjson.JSON;
import com.game.data.redis.RedisFactory;
import com.game.entity.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataCenter {

    public static final DataCenter INSTANCE = new DataCenter();

    public static DataCenter getInstance(){
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
