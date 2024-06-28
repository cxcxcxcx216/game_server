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

    @Override
    public String toString() {
        return "PlayerData{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", touchTime=" + touchTime +
                '}';
    }
}
