package com.game.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Player extends PlayerData{


    @JSONField(name = "bag")
    private Bag bag;

    @JSONField(serialize = false)
    private PlayerActor actor;

    public static Player create(){
        Player player = new Player();
        player.setBag(new Bag());
        player.setCreateTime(System.currentTimeMillis());
        player.setTouchTime(player.getCreateTime());
        return player;
    }

    @Override
    public String toString() {
        return "Player{" +
                "bag=" + bag +
                '}';
    }
}
