package com.game;

import com.game.consumer.ProtoMessageConsumer;
import com.game.data.redis.RedisFactory;
import com.game.event.BufferEvent;
import com.game.handler.proto.ProtoHandlerFactory;
import com.game.net.pipline.ProtoPipeline;
import com.game.net.server.BaseServer;
import com.game.processor.HeartProcessor;
import com.game.processor.MsgProcessorFactory;
import com.game.processor.SystemProcessorFactory;
import com.game.event.SystemEvent;
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

    public void run(){
        //创建服务器
        ProtoHandlerFactory.init();//初始化handler
        MsgProcessorFactory.init();

        //添心跳检测
        SystemEvent.exe();
        //buff检测器
        BufferEvent.exe();
        //链接redis
        RedisFactory.init();
        SystemProcessorFactory.init();//初始化处理器

        LogicServer server = new LogicServer();
        ProtoMessageConsumer consumer = new ProtoMessageConsumer();
        ProtoPipeline pipline = new ProtoPipeline();
        pipline.setConsumer(consumer);
        server.setPort(9001);
        server.setServerBootstrap(new ServerBootstrap());
        server.setBossGroup(new NioEventLoopGroup(1));
        server.setWorkerGroup(new NioEventLoopGroup(1));
        server.setChannelChannelInitializer(pipline);
        ShoutDownHook shoutDownHook = new ShoutDownHook();
        shoutDownHook.attachShutDownHook();
        server.start();
    }

    public static void main(String[] args) {
        LogicServer server = new LogicServer();
        server.run();
    }
}
