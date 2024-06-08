package com.game.handler;

import com.game.msg.ProtoMsg;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;

public abstract class ProtoBaseHandler {

    //执行逻辑
    public abstract void doAction(ChannelHandlerContext ctx, ProtoMsg msg) throws InvalidProtocolBufferException;

    //获取对应的处理器id
    public abstract int getProcessorId();

}
