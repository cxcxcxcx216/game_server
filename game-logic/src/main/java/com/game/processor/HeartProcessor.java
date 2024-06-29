package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import com.game.task.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@Processor(processorId = ProcessorId.Heart_PROCESSOR)
public class HeartProcessor implements IProcessor {
    private static final ScheduledThreadPoolExecutor executor = ThreadUtil.createScheduledExecutor(1);

    static {
        executor.setThreadFactory(ThreadUtil.createThreadFactory("Heart_PROCESSOR-"));
    }
    @Override
    public void execute(Task task) {
        ThreadUtil.schedule(executor,task,10,1, TimeUnit.SECONDS,true);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.Heart_PROCESSOR;
    }

    @Override
    public void init() {
        log.info("心跳检测线程初始化...");
    }

    @Override
    public void shoutDown() {
        executor.shutdown();
    }

    public static void addTask(Runnable task){
        ThreadUtil.schedule(executor,task,10,1, TimeUnit.SECONDS,true);
    }
}
