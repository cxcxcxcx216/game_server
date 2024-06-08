package com.game.action;

import cn.hutool.core.util.IdUtil;
import com.game.annotation.Action;
import com.game.cache.PlayerManager;
import com.game.entity.Player;
import com.game.handler.proto.ProtoBaseHandler;
import com.game.msg.ProtoMsg;
import com.game.net.BaseSession;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.Code.LoginMessageReq_VALUE)
public class LoginHandlerProto extends ProtoBaseHandler {
    @Override
    public void doAction(Session session, ProtoMsg msg) throws InvalidProtocolBufferException {

        ProtoMessage.LoginMessageReq reqMessage = ProtoMessage.LoginMessageReq.parseFrom(msg.getData());

        String name = reqMessage.getName();
        String password = reqMessage.getPassword();

        Player player = new Player();
        player.setName(name);
        player.setPid(IdUtil.getSnowflakeNextId());
        player.setSession(session);
        session.setPlayer(player);


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
        session.sendMessage(MsgCode.Code.LoginMessageRes_VALUE,builder.build().toByteArray());

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
