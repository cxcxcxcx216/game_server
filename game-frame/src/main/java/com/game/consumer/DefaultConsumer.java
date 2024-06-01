package com.game.consumer;

import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DefaultConsumer implements Consumer{
    @Override
    public void consume(ChannelHandlerContext ctx, ProtoMsg msg) {
        log.info("ip = {}，消息={}",ctx.channel().remoteAddress().toString(),msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("{}链接成功",ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("{}断开链接",ctx.channel().remoteAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("{}异常断开链接，异常={}",ctx.channel().remoteAddress().toString(),cause.toString());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        log.info("{} 触发事件，事件={}",ctx.channel().remoteAddress().toString(),evt.toString());
    }
}
