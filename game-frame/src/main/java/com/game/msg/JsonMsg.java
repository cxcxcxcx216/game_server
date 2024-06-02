package com.game.msg;

import lombok.Data;

@Data
public class JsonMsg {

    private short code;

    private short head;

    private byte[] data;

    private Object body;
}
