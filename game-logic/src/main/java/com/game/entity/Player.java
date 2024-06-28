package com.game.entity;


import com.game.net.BaseSession;
import com.game.net.Session;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Player extends PlayerData{

    private Bag bag;

    public static Player create(){
        Player player = new Player();
        player.setBag(new Bag());
        return player;
    }
}
