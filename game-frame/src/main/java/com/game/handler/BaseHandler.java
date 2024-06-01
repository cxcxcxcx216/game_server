package com.game.handler;

public abstract class BaseHandler implements Runnable{

    //执行逻辑
    public abstract void doAction();

    //获取对应的处理器id
    public abstract int getProcessorId();

    @Override
    public void run() {
        doAction();
    }
}
