package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;

@Processor(processorId = ProcessorId.PLAYER_PROCESSOR)
public class PlayerProcessor implements IProcessor {
    private static final ExecutorService executor = ThreadUtil.newExecutor(16,16,100);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.PLAYER_PROCESSOR;
    }
}