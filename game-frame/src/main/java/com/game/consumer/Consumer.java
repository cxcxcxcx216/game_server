package com.game.consumer;

import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

public interface Consumer {
    void consume(ChannelHandlerContext ctx, Object msg);

    void channelActive(ChannelHandlerContext ctx);

    void channelInactive(ChannelHandlerContext ctx);

    void exceptionCaught(ChannelHandlerContext ctx,Throwable cause);

    void userEventTriggered(ChannelHandlerContext ctx, Object evt);

}
