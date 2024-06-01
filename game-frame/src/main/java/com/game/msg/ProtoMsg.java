package com.game.msg;

import lombok.Data;

@Data
public class ProtoMsg {

    private short code;

    private short head;

    private byte[] data;

}
