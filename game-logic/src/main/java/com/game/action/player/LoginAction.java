package com.game.action.player;

import cn.hutool.core.util.IdUtil;
import com.game.annotation.Action;
import com.game.constant.ProcessorId;
import com.game.manager.PlayerManager;
import com.game.entity.Bag;
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
public class LoginAction extends BaseAction {
    @Override
    public void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException {
        ProtoMessage.LoginMessageReq reqMessage = ProtoMessage.LoginMessageReq.parseFrom(msg.getData());

        String name = reqMessage.getName();
        Player player = Player.create();
        player.setName(name);
        player.setPid(IdUtil.getSnowflakeNextId());
        player.setSession(session);
        Bag bag = Bag.create(player);
        player.setBag(bag);
        session.setPlayer(player);
//        DataCenter.getInstance().savePlayer(player);

        ProtoMessage.LoginMessageRes.Builder builder = ProtoMessage.LoginMessageRes.newBuilder();
        if (PlayerManager.getInstance().createPlayer(player)) {
            log.info("账号登录成功");
            builder.setName(player.getName());
            builder.setErrorCode(0);
        }else {
            log.error("账号登录失败");
            builder.setName(player.getName());
            builder.setErrorCode(1001);
        }
        //返回客户端消息
        MsgUtil.sendMsg(player.getPid(),MsgCode.Code.LoginMessageRes_VALUE,builder.build().toByteArray());

    }

    @Override
    public int getProcessor() {
        return ProcessorId.PLAYER_PROCESSOR;
    }

}
