package com.game.action.player;


import com.game.annotation.Action;
import com.game.constant.ProcessorId;
import com.game.manager.PlayerManager;
import com.game.handler.proto.BaseAction;
import com.game.msg.ProtoMsg;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.Code.LoginMessageReq_VALUE)
public class LoginAction extends BaseAction {
    @Override
    public void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException {
        ProtoMessage.LoginMessageReq reqMessage = ProtoMessage.LoginMessageReq.parseFrom(msg.getData());
        String name = reqMessage.getName();
        PlayerManager.getInstance().login(name,session);
    }

    @Override
    public int getProcessor() {
        return ProcessorId.PLAYER_PROCESSOR;
    }

}
