package com.game.action.player;

import com.game.annotation.Action;
import com.game.entity.Player;
import com.game.handler.proto.BaseAction;
import com.game.manager.PlayerManager;
import com.game.msg.ProtoMsg;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.game.utils.MsgUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.Code.LogoutMessageReq_VALUE)
public class LogoutAction extends BaseAction {
    @Override
    public void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException {
        ProtoMessage.LogoutMessageReq reqMessage = ProtoMessage.LogoutMessageReq.parseFrom(msg.getData());
        Player player = session.getPlayer();
        boolean logout = PlayerManager.getInstance().logout(player.getPid());

        ProtoMessage.LogoutMessageRes.Builder builder = ProtoMessage.LogoutMessageRes.newBuilder();
        builder.setResult(logout);
        MsgUtil.sendMsg(player.getPid(),MsgCode.Code.LogoutMessageRes_VALUE,builder.build().toByteArray());
    }
}
