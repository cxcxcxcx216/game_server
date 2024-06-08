package com.game.handler.proto;

import com.game.msg.ProtoMsg;
import com.game.net.BaseSession;
import com.game.net.Session;
import com.google.protobuf.InvalidProtocolBufferException;

public abstract class ProtoBaseHandler {

    //执行逻辑
    public abstract void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException;

    //获取对应的处理器id
    public abstract int getProcessorId();

}