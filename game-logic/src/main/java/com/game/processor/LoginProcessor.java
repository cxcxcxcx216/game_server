package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import com.game.task.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 默认为单线程处理
 */
@Slf4j
@Processor(processorId = ProcessorId.SYSTEM_PROCESSOR)
public class LoginProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newFixedExecutor(8,1024,"SYSTEM_PROCESSOR-",false);

    @Override
    public void execute(Task task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.SYSTEM_PROCESSOR;
    }

    @Override
    public void init() {
        log.info("登录线程初始化...");
    }

    @Override
    public void shoutDown() {
        executor.shutdown();
    }


}
