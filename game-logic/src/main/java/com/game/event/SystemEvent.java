package com.game.event;


import com.game.entity.Player;
import com.game.handler.HandlerFactory;
import com.game.manager.PlayerManager;
import com.game.processor.HeartProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 系统任务
 */
@Slf4j
public class SystemEvent implements IEvent{
    @Override
    public void run() {
        if (PlayerManager.getInstance().getPlayerMap() != null) {
            log.info("玩家数量={}",PlayerManager.getInstance().getPlayerMap().size());
            for (Map.Entry<Long, Player> entry : PlayerManager.getInstance().getPlayerMap().entrySet()) {
//                log.info("pid = {}",entry.getValue().getPid());
            }
        }
    }

    @Override
    public void execute() {
        HeartProcessor.addTask(this);
    }

    public static void exe(){
        SystemEvent event = new SystemEvent();
        event.execute();
    }
}
