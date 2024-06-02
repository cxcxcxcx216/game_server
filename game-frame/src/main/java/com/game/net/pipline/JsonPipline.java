package com.game.net.pipline;

import com.game.consumer.Consumer;
import com.game.net.handler.JsonInboundHandler;
import com.game.net.handler.JsonMessageDecoder;
import com.game.net.handler.JsonMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;


@Data
public class JsonPipline extends ChannelInitializer<SocketChannel> {

    private Consumer consumer;
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        JsonInboundHandler handler = new JsonInboundHandler();
        handler.setConsumer(consumer);
        ch.pipeline().addLast(new JsonMessageDecoder())
                .addLast(new JsonMessageEncoder())
                .addLast(handler);
    }
}
