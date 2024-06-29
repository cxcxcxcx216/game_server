package com.game.processor;


import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Processor(processorId = ProcessorId.Buffer_PROCESSOR)
public class BufferProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newFixedExecutor(1,1024,"Buffer_PROCESSOR-",false);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.Buffer_PROCESSOR;
    }

    @Override
    public void shoutDown() {
        executor.shutdown();
    }

    public static void addTask(Runnable task){
        executor.execute(task);
    }


}
