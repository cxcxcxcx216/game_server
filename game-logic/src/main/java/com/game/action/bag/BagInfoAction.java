package com.game.action.bag;


import com.game.annotation.Action;
import com.game.constant.ProcessorId;
import com.game.entity.Player;
import com.game.handler.proto.BaseAction;
import com.game.msg.ProtoMsg;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.game.system.bag.Item;
import com.game.utils.MsgUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Action(msgId = MsgCode.Code.BagInfoMessageReq_VALUE)
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
        for (Map.Entry<Long, Item> entry : session.getPlayer().getBag().getItemMap().entrySet()) {
            ProtoMessage.Item.Builder item = ProtoMessage.Item.newBuilder();
            item.setId(entry.getValue().getId())
                    .setCount(entry.getValue().getCount())
                    .setTime(entry.getValue().getTime())
                    .setItemId(entry.getValue().getItemId());
            bagInfo.addItems(item);
        }
        MsgUtil.sendMsg(player.getPid(),MsgCode.Code.BagInfoMessageRes_VALUE,bagInfo.build().toByteArray());
    }

    @Override
    public int getProcessor() {
        return ProcessorId.SYSTEM_PROCESSOR;
    }
}
