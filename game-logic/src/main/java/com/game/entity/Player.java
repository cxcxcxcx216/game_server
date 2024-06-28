package com.game.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Player extends PlayerData{

    private Bag bag;

    private PlayerActor actor;

    public static Player create(){
        Player player = new Player();
        player.setBag(new Bag());
        return player;
    }
}
