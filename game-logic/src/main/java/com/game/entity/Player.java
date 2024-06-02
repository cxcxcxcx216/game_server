package com.game.entity;


import com.game.net.Session;
import lombok.Data;

@Data
public class Player {

    private Session session;

    private long pid;

    private long name;

    private long createTime;


}
