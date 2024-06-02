package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.processor.common.IProcessor;

import java.util.concurrent.ExecutorService;

/**
 * 默认为单线程处理
 */
public class SystemProcessor implements IProcessor {

    private static final ExecutorService executor = ThreadUtil.newExecutor(1,1,100);

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }

    @Override
    public int getProcessorId() {
        return 0;
    }


}
