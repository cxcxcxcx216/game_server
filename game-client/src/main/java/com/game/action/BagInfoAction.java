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
        for (ProtoMessage.Item item : messageRes.getItemsList()) {
            log.info("id = {},itemId = {},count = {},time = {}",item.getId(),item.getItemId(),item.getCount(),item.getTime());
        }

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
