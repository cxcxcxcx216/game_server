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
@Action(msgId = MsgCode.Code.BagInfoMessageRes_VALUE)
public class BagInfoAction extends ProtoBaseHandler {
    @Override
    public void doAction(ChannelHandlerContext ctx, ProtoMsg msg) throws InvalidProtocolBufferException {
        ProtoMessage.BagInfoRes messageRes = ProtoMessage.BagInfoRes.parseFrom(msg.getData());
        log.info("服务器返回背包信息！bag = {}",messageRes.getItemsList());
    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
