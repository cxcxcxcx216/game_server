package com.game.handler.action;

import com.game.annotation.Action;
import com.game.entity.Player;
import com.game.handler.BaseHandler;
import com.game.msg.JsonMsg;

@Action(msgId = 1001)
public class LoginHandler extends BaseHandler {
    @Override
    public void doAction(Player player, JsonMsg msg) {

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
