package com.game;

import com.game.proto.ProtoMessage;
import io.netty.channel.ChannelHandlerContext;

public class Client {

    public static ChannelHandlerContext ctx;
    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NettyClient client = new NettyClient("127.0.0.1", 9001);
                try {
                    client.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Thread.sleep(1000);

        ProtoMessage.LoginMessageReq.Builder builder = ProtoMessage.LoginMessageReq.newBuilder();
        builder.setName("chencing");
        builder.setPassword("123123");
        ProtoMessage.LoginMessageReq build = builder.build();
        byte[] bytes = build.toByteArray();



    }
}
