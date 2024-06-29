package com.game.entity;

import com.game.system.buffer.Buff;
import com.game.system.buffer.BuffManager;
import lombok.Data;

import java.util.List;


@Data
public class PlayerActor extends BaseActor{

    private String name;

    private int health;

    private BuffManager buffManager;

    public static PlayerActor create(Player player){
        PlayerActor actor = new PlayerActor();
        actor.setName(player.getName());
        actor.setId(player.getPid());
        return actor;
    }
}
