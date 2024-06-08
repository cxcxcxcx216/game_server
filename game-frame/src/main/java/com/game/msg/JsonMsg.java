package com.game.msg;


import com.game.net.BaseSession;
import lombok.Data;

@Data
public class JsonMsg {

    private short code;

    private short head;

    private byte[] data;

    private Object body;

    private BaseSession session;

}
