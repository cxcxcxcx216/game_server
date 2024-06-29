package com.game.system.room;

import com.game.entity.Player;
import com.game.util.IDUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Data
@Slf4j
public class Room {

    private Player master;

    private long roomId;

    private int status;

    private Set<Player> players = new HashSet<>();

    public static  Room create(Player player){
        log.info("create room");
        Room room = new Room();
        room.setRoomId(IDUtil.generateSnowflakeId());
        room.setMaster(player);
        room.getPlayers().add(player);
        return room;
    }

    public int addPlayer(Player player){
        log.info("玩家 = {}，加入房间",player.getPid());
        players.add(player);
        return players.size();
    }

    public int removePlayer(Player player){
        log.info("玩家 = {}，退出房间",player.getPid());
        players.remove(player);
        return players.size();
    }




}
