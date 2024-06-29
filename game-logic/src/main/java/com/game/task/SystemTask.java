package com.game.task;


import com.game.entity.Player;
import com.game.manager.PlayerManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 系统任务
 */
@Slf4j
public class SystemTask implements Runnable{
    @Override
    public void run() {
        if (PlayerManager.getInstance().getPlayerMap() != null) {
            log.info("玩家数量={}",PlayerManager.getInstance().getPlayerMap().size());
            for (Map.Entry<Long, Player> entry : PlayerManager.getInstance().getPlayerMap().entrySet()) {
                log.info("pid = {}",entry.getValue().getPid());
            }
        }
    }
}
