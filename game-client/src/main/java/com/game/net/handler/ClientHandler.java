package com.game.net.handler;


import com.game.consumer.Consumer;
import com.game.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@AllArgsConstructor
public class ClientHandler extends SimpleChannelInboundHandler<ProtoMsg> {

    private Consumer consumer;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMsg msg) {
        log.info("服务器返回消息号:{}",msg.getCode());
        consumer.consume(ctx,msg);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        consumer.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务器断开链接");
        consumer.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        consumer.exceptionCaught(ctx,cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        consumer.userEventTriggered(ctx,evt);
    }
}
