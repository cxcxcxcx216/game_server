package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.*;

@Processor(processorId = ProcessorId.Heart_PROCESSOR)
public class HeartProcessor implements IProcessor {

    private static final ScheduledExecutorService service
            = Executors.newScheduledThreadPool(1,ThreadUtil.createThreadFactory("Heart_PROCESSOR-"));


    @Override
    public void execute(Runnable task) {
        service.scheduleAtFixedRate(task,10,1, TimeUnit.SECONDS);
    }

    @Override
    public int getProcessorId() {
        return 0;
    }

    public static void addTask(Runnable task){
        service.scheduleAtFixedRate(task,10,1, TimeUnit.SECONDS);
    }
}
