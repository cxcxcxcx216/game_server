package com.game.net.handler;


import com.game.consumer.Consumer;
import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerInboundHandler extends SimpleChannelInboundHandler<ProtoMsg> {

    private Consumer consumer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMsg msg){
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
