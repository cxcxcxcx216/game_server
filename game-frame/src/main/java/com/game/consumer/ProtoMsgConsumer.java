package com.game.consumer;

import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProtoMsgConsumer implements Consumer{
    @Override
    public void consume(ChannelHandlerContext ctx, ProtoMsg reqProtoMsg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {

    }
}
