package com.game.net.pipline;

import com.game.net.handler.JsonInboundHandler;
import com.game.net.handler.JsonMessageDecoder;
import com.game.net.handler.JsonMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class JsonPipline extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new JsonMessageDecoder())
                .addLast(new JsonMessageEncoder())
                .addLast(new JsonInboundHandler());
    }
}
