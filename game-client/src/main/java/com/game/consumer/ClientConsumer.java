package com.game.consumer;

import com.game.Client;
import com.game.handler.ProtoBaseHandler;
import com.game.handler.ClientProtoHandlerFactory;
import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientConsumer implements Consumer{
    @Override
    public void consume(ChannelHandlerContext ctx, Object msg) {
        log.info("客户端收到消息");
        ProtoMsg protoMsg = (ProtoMsg) msg;
        ProtoBaseHandler handler = ClientProtoHandlerFactory.getHandler(protoMsg.getCode());
        try {
            handler.doAction(ctx,protoMsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("链接成功,ip = {}",ctx.channel().remoteAddress());
        Client.ctx = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("断开链接,ip = {}",ctx.channel().remoteAddress());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("断开链接,ip = {}",ctx.channel().remoteAddress());
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            log.info("断开链接,ip = {}",ctx.channel().remoteAddress());
            ctx.close();
        }
    }
}
