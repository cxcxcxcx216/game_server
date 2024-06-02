package com.game.net.rpc.comsumer;

import com.game.consumer.Consumer;
import com.game.msg.RpcMsg;
import io.netty.channel.ChannelHandlerContext;

public class RpcConsumer implements Consumer {
    @Override
    public void consume(ChannelHandlerContext ctx, Object msg) {
        RpcMsg rpcMsg = (RpcMsg) msg;

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
