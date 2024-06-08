package com.game.entity;


import com.game.net.BaseSession;
import com.game.net.Session;
import lombok.Builder;
import lombok.Data;

@Data
public class Player {


    private long pid;

    private String name;

    private long createTime;

    private long touchTime;

    private Session session;

}
