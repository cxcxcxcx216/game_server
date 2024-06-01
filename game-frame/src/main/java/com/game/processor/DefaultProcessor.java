package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 默认为单线程处理
 */
public class DefaultProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newExecutor(1,1,100);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }
}
