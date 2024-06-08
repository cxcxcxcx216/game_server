package com.game.handler.proto;

import com.game.entity.Player;
import com.game.msg.JsonMsg;
import com.game.msg.ProtoMsg;

public abstract class ProtoBaseHandler {

    //执行逻辑
    public abstract void doAction(Player player, ProtoMsg msg);

    //获取对应的处理器id
    public abstract int getProcessorId();

}
