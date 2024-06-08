package com.game.msg;

import com.game.net.server.Session;
import lombok.Data;

@Data
public class ProtoMsg {

    private short code;

    private short head;

    private byte[] data;

    private Object body;

    private Session session;

}
