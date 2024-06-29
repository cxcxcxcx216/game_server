package com.game;

import com.game.handler.ClientProtoHandlerFactory;
import com.game.msg.ProtoMsg;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import io.netty.channel.ChannelHandlerContext;

public class Client {

    public static ChannelHandlerContext ctx;
    public static void main(String[] args) throws Exception {

        ClientProtoHandlerFactory.init();
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
        Thread.sleep(5000);


        while (true){
            Thread.sleep(1);
            ProtoMessage.LoginMessageReq.Builder builder = ProtoMessage.LoginMessageReq.newBuilder();
            builder.setName("chenxing");
            builder.setPassword("123123");
            ProtoMessage.LoginMessageReq build = builder.build();
            ProtoMsg protoMsg = new ProtoMsg();
            protoMsg.setHead((short) 0);
            protoMsg.setCode((short) MsgCode.Code.LoginMessageReq_VALUE);
            protoMsg.setData(build.toByteArray());
            ctx.writeAndFlush(protoMsg);


            Thread.sleep(1);
            ProtoMessage.BagInfoReq.Builder builder1 = ProtoMessage.BagInfoReq.newBuilder();
            ProtoMessage.BagInfoReq build1 = builder1.build();
            ProtoMsg protoMsg1 = new ProtoMsg();
            protoMsg1.setHead((short) 0);
            protoMsg1.setCode((short) MsgCode.Code.BagInfoMessageReq_VALUE);
            protoMsg1.setData(build1.toByteArray());
            ctx.writeAndFlush(protoMsg1);
        }


    }
}
