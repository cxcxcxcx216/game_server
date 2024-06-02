package com.game.consumer;

import com.game.handler.BaseHandler;
import com.game.handler.HandlerFactory;
import com.game.msg.ProtoMsg;
import lombok.Data;

@Data
public class Task implements Runnable{

    private ProtoMsg msg;

    @Override
    public void run() {
        BaseHandler handler = HandlerFactory.getHandler(msg.getCode());
        handler.doAction();
    }

    public static Task createTask(){
        return new Task();
    }
}
