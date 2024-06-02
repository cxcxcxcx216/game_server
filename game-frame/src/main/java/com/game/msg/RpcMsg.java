package com.game.msg;

import lombok.Data;

@Data
public class RpcMsg {

    private int msgId;

    private Object data;
}
