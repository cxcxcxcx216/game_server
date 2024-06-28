package com.game.action.bag;


import com.game.annotation.Action;
import com.game.cache.PlayerManager;
import com.game.entity.Player;
import com.game.handler.proto.BaseAction;
import com.game.msg.ProtoMsg;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.game.utils.MsgUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.Code.LoginMessageReq_VALUE)
public class BagInfoAction extends BaseAction {
    @Override
    public void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException {
        log.info("玩家获取背包信息");
        ProtoMessage.BagInfoReq bagInfoReq = ProtoMessage.BagInfoReq.parseFrom(msg.getData());
        Player player = session.getPlayer();
        if (player == null) {
            log.error("player = {},is null",session);
            return;
        }
        ProtoMessage.BagInfoRes.Builder bagInfo = player.getBag().build();
        MsgUtil.sendMsg(player.getPid(),MsgCode.Code.BagInfoMessageRes_VALUE,bagInfo.build().toByteArray());
    }
}
