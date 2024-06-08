package com.game.action;

import com.game.annotation.Action;
import com.game.entity.Player;
import com.game.handler.json.JsonBaseHandler;
import com.game.msg.JsonMsg;

@Action(msgId = 1001)
public class LoginHandlerJson extends JsonBaseHandler {
    @Override
    public void doAction(Player player, JsonMsg msg) {

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
