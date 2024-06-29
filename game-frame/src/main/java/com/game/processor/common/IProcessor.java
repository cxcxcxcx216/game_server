package com.game.processor.common;

import com.game.task.Task;

public interface IProcessor {

    void execute(Task task);

    int getProcessorId();

    void init();

    void shoutDown();
}
