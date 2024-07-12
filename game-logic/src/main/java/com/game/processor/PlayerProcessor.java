package com.game.processor;

import cn.hutool.core.thread.ThreadUtil;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import com.game.task.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
@Slf4j
@Processor(processorId = ProcessorId.PLAYER_PROCESSOR)
public class PlayerProcessor implements IProcessor {
    private static final List<ExecutorService> threadList =  new ArrayList<>();
    @Override
    public void execute(Task task) {
        int index = (int)(task.getMsg().getBaseSession().getId() % threadList.size());
        threadList.get(index).execute(task);
    }

    @Override
    public int getProcessorId() {
        return ProcessorId.PLAYER_PROCESSOR;
    }

    @Override
    public void init() {
        log.info("threadList size = {}",threadList.size());
        if(threadList.size() == 0){
            for (int i = 0; i < 1; i++) {
                ExecutorService executor = ThreadUtil.newFixedExecutor(1,1024*1024,"PLAYER_PROCESSOR-"+i,false);
                log.info("添加玩家处理线程，thread = {}",executor.isShutdown());
                threadList.add(i,executor);
            }
        }
    }

    @Override
    public void shoutDown() {
        for (ExecutorService executorService : threadList) {
            executorService.shutdown();
        }
        threadList.clear();
    }
}
