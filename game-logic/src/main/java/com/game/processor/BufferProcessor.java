package com.game.processor;


import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import com.game.task.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Slf4j
@Processor(processorId = ProcessorId.Buffer_PROCESSOR)
public class BufferProcessor implements IProcessor {

    private static final ScheduledThreadPoolExecutor executor = ThreadUtil.createScheduledExecutor(1);

    static {
        executor.setThreadFactory(ThreadUtil.createThreadFactory("Buffer_PROCESSOR-"));
    }
    @Override
    public void execute(Task task) {
        ThreadUtil.schedule(executor,task,10,1, TimeUnit.SECONDS,true);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.Buffer_PROCESSOR;
    }

    @Override
    public void init() {
        log.info("buff检测线程初始化...");
    }

    @Override
    public void shoutDown() {
        executor.shutdown();
    }

    public static void addTask(Runnable task){
        ThreadUtil.schedule(executor,task,10,1, TimeUnit.SECONDS,true);
    }


}
