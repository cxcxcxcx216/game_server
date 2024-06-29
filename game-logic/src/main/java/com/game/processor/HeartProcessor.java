package com.game.processor;

import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Processor(processorId = ProcessorId.Heart_PROCESSOR)
public class HeartProcessor implements IProcessor {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

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
