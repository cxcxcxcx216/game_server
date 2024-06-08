package com.game.handler.json;

import com.game.entity.Player;
import com.game.msg.JsonMsg;

public abstract class JsonBaseHandler {

    //执行逻辑
    public abstract void doAction(Player player, JsonMsg msg);

    //获取对应的处理器id
    public abstract int getProcessorId();

}
