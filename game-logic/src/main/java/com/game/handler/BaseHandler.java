package com.game.handler;

import com.game.entity.Player;
import com.game.msg.JsonMsg;

public abstract class BaseHandler{

    //执行逻辑
    public abstract void doAction(Player player, JsonMsg msg);

    //获取对应的处理器id
    public abstract int getProcessorId();

}
