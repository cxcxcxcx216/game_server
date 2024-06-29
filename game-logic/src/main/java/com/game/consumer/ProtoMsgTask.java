package com.game.consumer;

import com.game.manager.SessionManager;
import com.game.constant.ProcessorId;

import com.game.handler.proto.BaseAction;
import com.game.handler.proto.ProtoHandlerFactory;

import com.game.net.Session;
import com.game.task.Task;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Data;

@Data
public class ProtoMsgTask extends Task {

    @Override
    public void run() {
        BaseAction handler = ProtoHandlerFactory.getHandler(msg.getCode());
        Session session = SessionManager.getInstance().getSession(msg.getBaseSession().getCtx());

        try {
            handler.doAction(session,msg);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

    }

    public static ProtoMsgTask createTask(){
        return new ProtoMsgTask();
    }

    public int getProcessorId(){
        return ProcessorId.SYSTEM_PROCESSOR;
    }
}
