package com.game.net.rpc.handler;


import com.game.consumer.Consumer;
import com.game.msg.ProtoMsg;
import com.game.msg.RpcMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class RpcServerInboundHandler extends SimpleChannelInboundHandler<RpcMsg> {

    private Consumer consumer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcMsg msg){
        consumer.consume(ctx,msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        consumer.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        consumer.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        consumer.exceptionCaught(ctx,cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        consumer.userEventTriggered(ctx, evt);
    }

}
