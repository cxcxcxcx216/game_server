package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;

/**
 * 默认为单线程处理
 */

@Processor(processorId = ProcessorId.SYSTEM_PROCESSOR)
public class SystemProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newExecutor(4,8,100);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return 0;
    }


}
