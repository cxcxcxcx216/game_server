package com.game.utils;

import com.game.manager.PlayerManager;
import com.game.entity.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgUtil {

    public static void sendMsg(long pid,int msgId, byte[] data){
        Player player = PlayerManager.getInstance().getPlayerById(pid);
        if (null == player) {
            log.error("player = {} is null!",pid);
            return;
        }
        player.getSession().sendMessage(msgId,data);
    }
}
