package com.game.event;

import com.game.processor.BufferProcessor;
import com.game.system.buffer.BuffManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 检测buff相关
 */
@Slf4j
public class BufferEvent implements IEvent{
    @Override
    public void run() {
        log.info("更新检测BUFF");
        BuffManager.getInstance().updateBuffs();
    }

    @Override
    public void execute() {
        BufferProcessor.addTask(this);

    }

    public static void exe(){
        BufferEvent event = new BufferEvent();
        event.execute();
    }
}
