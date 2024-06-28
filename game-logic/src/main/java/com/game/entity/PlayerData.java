package com.game.entity;

import com.game.net.Session;
import lombok.Data;

@Data
public abstract class PlayerData {
    private long pid;

    private String name;

    private long createTime;

    private long touchTime;

    private Session session;
}
