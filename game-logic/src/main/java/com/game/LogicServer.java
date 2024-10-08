package com.game;

import com.game.constant.GameConstant;
import com.game.consumer.ProtoMessageConsumer;
import com.game.data.mongo.MongoDBFactory;
import com.game.data.redis.RedisFactory;
import com.game.handler.proto.ProtoHandlerFactory;
import com.game.listener.EventUtil;
import com.game.net.pipline.ProtoPipeline;
import com.game.net.server.BaseServer;
import com.game.processor.factory.MsgProcessorFactory;
import com.game.processor.factory.SystemProcessorFactory;
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
        //链接redis
        RedisFactory.init();
//        MongoDBFactory.init();

        SystemProcessorFactory.init();//初始化处理

        EventUtil.init(GameConstant.Pkg.LISTENER_PATH);//注册监听器

        LogicServer server = new LogicServer();
        ProtoMessageConsumer consumer = new ProtoMessageConsumer();
        ProtoPipeline pipline = new ProtoPipeline();
        pipline.setConsumer(consumer);
        server.setPort(9001);
        server.setServerBootstrap(new ServerBootstrap());
        server.setBossGroup(new NioEventLoopGroup(1));
        server.setWorkerGroup(new NioEventLoopGroup(8));
        server.setChannelChannelInitializer(pipline);
        server.start();
    }

    public static void main(String[] args) {
        LogicServer server = new LogicServer();
        ShoutDownHook shoutDownHook = new ShoutDownHook();
        shoutDownHook.attachShutDownHook();


        server.run();
    }
}
