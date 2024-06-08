package com.game.action;

import com.game.annotation.Action;
import com.game.handler.ProtoBaseHandler;
import com.game.msg.ProtoMsg;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Action(msgId = MsgCode.Code.LoginMessageRes_VALUE)
public class LoginAction extends ProtoBaseHandler {

    @Override
    public void doAction(ChannelHandlerContext ctx, ProtoMsg msg) throws InvalidProtocolBufferException {

        ProtoMessage.LoginMessageRes messageRes = ProtoMessage.LoginMessageRes.parseFrom(msg.getData());
        String name = messageRes.getName();
        if(messageRes.getErrorCode() == 0){
            log.info("登录成功",name);
        }

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
