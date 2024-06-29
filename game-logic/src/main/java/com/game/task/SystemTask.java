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
        log.info(PlayerManager.getInstance().getPlayerMap().toString());
    }
}
