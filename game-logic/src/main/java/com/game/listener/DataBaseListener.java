package com.game.listener;


import com.game.annotation.Listener;
import com.game.constant.GameConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Listener(type = GameConstant.Listener.DATA_BASE)
public class DataBaseListener implements IListener {
    @Override
    public void update(Object param) {
        log.info("更新数据库事件");
    }
}
