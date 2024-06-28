package com.game.cache;


import com.game.entity.Player;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Slf4j
public class PlayerManager {

    private static final Map<Long, Player> PLAYER_MAP = new ConcurrentHashMap<>();

    private static final PlayerManager INSTANCE = new PlayerManager();

    public static PlayerManager getInstance(){
        return INSTANCE;
    }


    public boolean createPlayer(Player player){
        player.setCreateTime(System.currentTimeMillis());
        PLAYER_MAP.put(player.getPid(), player);
        log.error("玩家数量 = {}",PLAYER_MAP.size());
        if(player != null){
            log.info("player create is successful ! player= {}",player.getName());
            return true;
        }
        return false;
    }

    public boolean logout(long pid){
        Player result = PLAYER_MAP.remove(pid);
        if(result != null){
            log.info("player logout is successful ! player= {}",result);
            return true;
        }
        log.info("player logout is fail ! player= {}",result.toString());
        return false;
    }

    public Player getPlayerById(long pid){
        Player player = PLAYER_MAP.getOrDefault(pid, null);
        return player;
    }
}


