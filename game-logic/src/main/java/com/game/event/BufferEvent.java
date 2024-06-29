package com.game.event;

import com.game.processor.BufferProcessor;

/**
 * 检测buff相关
 */
public class BufferEvent implements IEvent{
    @Override
    public void run() {

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
