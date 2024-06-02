package com.game;

import com.game.consumer.JsonMessageConsumer;
import com.game.net.pipline.JsonPipline;
import com.game.net.server.BaseServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;

@Data
public class LogicServer extends BaseServer {


    public LogicServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup, ServerBootstrap serverBootstrap, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        super(port, bossGroup, workerGroup, serverBootstrap, channelChannelInitializer);
    }

    public LogicServer(){
    }

    public static void main(String[] args) {

        //创建服务器
        LogicServer server = new LogicServer();
        JsonMessageConsumer consumer = new JsonMessageConsumer();
        JsonPipline pipline = new JsonPipline();
        pipline.setConsumer(consumer);
        server.setPort(9001);
        server.setServerBootstrap(new ServerBootstrap());
        server.setBossGroup(new NioEventLoopGroup(1));
        server.setWorkerGroup(new NioEventLoopGroup(1));
        server.setChannelChannelInitializer(pipline);
        server.start();
    }
}
