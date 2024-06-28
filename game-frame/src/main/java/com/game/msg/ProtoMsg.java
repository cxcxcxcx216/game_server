package com.game.msg;

import com.game.net.BaseSession;
import lombok.Data;


@Data
public class ProtoMsg {

    private short code;

    private short head;

    private byte[] data;

    private Object body;

    private BaseSession baseSession;


}
