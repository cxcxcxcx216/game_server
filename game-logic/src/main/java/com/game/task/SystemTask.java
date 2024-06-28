package com.game.task;


import com.game.entity.Player;
import com.game.manager.PlayerManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 系统任务
 */
@Slf4j
public class SystemTask implements Runnable{
    @Override
    public void run() {
        while (true){
            //检测在线玩家
            Map<Long, Player> playerMap = PlayerManager.getInstance().getPlayerMap();
            for (Map.Entry<Long, Player> entry : playerMap.entrySet()) {
                log.info("player = {}",entry.getKey());
            }
        }

    }
}
