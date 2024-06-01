package com.game.net;

import com.game.consumer.DefaultConsumer;
import com.game.handler.MessageDecoder;
import com.game.handler.MessageEncoder;
import com.game.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BaseServer {
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelInitializer<SocketChannel> channelChannelInitializer;

    public BaseServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup, ServerBootstrap serverBootstrap, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.serverBootstrap = serverBootstrap;
        this.channelChannelInitializer = channelChannelInitializer;
    }

    public BaseServer() {

    }

    public void start(){

    }

    /**
     * 通过默认设置启动
     */
    public void startDefault(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        log.info("服务器启动中...");

        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup);

            serverBootstrap.channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MessageDecoder())
                                    .addLast(new MessageEncoder())
                                    .addLast(new ServerHandler(new DefaultConsumer()));
                        }
                    });


            ChannelFuture channelFuture = serverBootstrap.bind(9001).sync();
            log.info("服务器启动成功！");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
