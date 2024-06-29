package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 默认为单线程处理
 */
public class DefaultProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newFixedExecutor(16,1024,"DefaultProcessor-",false);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return 0;
    }

    @Override
    public void shoutDown() {
        executor.shutdown();
    }
}
