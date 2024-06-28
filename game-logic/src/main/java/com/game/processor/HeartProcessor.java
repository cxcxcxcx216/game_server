package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;

@Processor(processorId = ProcessorId.Heart_PROCESSOR)
public class HeartProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newExecutor(1,1,10);

    @Override
    public void execute(Runnable task) {

    }

    @Override
    public int getProcessorId() {
        return 0;
    }
}
