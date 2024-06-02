package com.game.net.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class RpcBaseServer {

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelInitializer<SocketChannel> channelChannelInitializer;


    public void start(){
        try {
            serverBootstrap.group(bossGroup,workerGroup);

            serverBootstrap.channel(NioServerSocketChannel.class)
                    .childHandler(channelChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            log.info("服务器启动成功！");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public void startDefault(){
        serverBootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        log.info("Rpc服务器启动中...");
        log.warn("通过默认配置启动...");
        try {
            serverBootstrap.group(bossGroup,workerGroup);

            serverBootstrap.channel(NioServerSocketChannel.class)
                    .childHandler(channelChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind(8001).sync();
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
