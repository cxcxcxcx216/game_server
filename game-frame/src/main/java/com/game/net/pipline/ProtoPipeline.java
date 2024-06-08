package com.game.net.pipline;

import com.game.consumer.Consumer;
import com.game.net.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;

@Data
public class ProtoPipeline extends ChannelInitializer<SocketChannel> {

    private Consumer consumer;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ServerInboundHandler handler = new ServerInboundHandler();
        handler.setConsumer(consumer);
        ch.pipeline().addLast(new MessageDecoder())
                .addLast(new MessageEncoder())
                .addLast(handler);
    }

}
