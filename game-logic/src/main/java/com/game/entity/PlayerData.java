package com.game.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.game.net.Session;
import lombok.Data;

@Data
public abstract class PlayerData {
    private long pid;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "createTime")
    private long createTime;

    @JSONField(name = "touchTime")
    private long touchTime;

    @JSONField(serialize = false)
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
