package com.game.listener;


import com.game.annotation.Listener;
import com.game.constant.GameConstant;
import com.game.data.DataCenter;
import com.game.entity.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Listener(type = GameConstant.Listener.DATA_BASE)
public class DataBaseListener implements IListener {
    @Override
    public void update(Object param) {
        if(param instanceof Player){
            Player player = (Player) param;
            log.info("存储玩家 = {} 数据",player.getPid());
            DataCenter.getInstance().savePlayer(player);

        }

    }
}
