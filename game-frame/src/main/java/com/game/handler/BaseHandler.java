package com.game.handler;

import com.game.msg.ProtoMsg;
import com.game.net.server.Session;

public abstract class BaseHandler{

    //执行逻辑
    public abstract void doAction();

    //获取对应的处理器id
    public abstract int getProcessorId();

}
