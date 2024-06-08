package com.game.action;

import com.game.annotation.Action;
import com.game.entity.Player;
import com.game.handler.proto.ProtoBaseHandler;
import com.game.msg.ProtoMsg;
import com.game.proto.MsgCode;

@Action(msgId = MsgCode.Code.Login_VALUE)
public class LoginHandlerProto extends ProtoBaseHandler {
    @Override
    public void doAction(Player player, ProtoMsg msg) {

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
