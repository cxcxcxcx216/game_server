package com.game.net.handler;

import com.alibaba.fastjson.JSON;
import com.game.consumer.Consumer;
import com.game.msg.JsonMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;


import java.util.Base64;

@Data
public class JsonInboundHandler extends SimpleChannelInboundHandler<JsonMsg> {
    private Consumer consumer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JsonMsg msg){
        String base64String = Base64.getEncoder().encodeToString(msg.getData());
        Object parse = JSON.parse(base64String);
        msg.setBody(parse);
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
