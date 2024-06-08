package com.game.consumer;

import com.game.cache.PlayerManager;
import com.game.constant.ProcessorId;
import com.game.entity.Player;
import com.game.handler.json.JsonBaseHandler;
import com.game.handler.json.JsonHandlerFactory;
import com.game.handler.proto.ProtoBaseHandler;
import com.game.handler.proto.ProtoHandlerFactory;
import com.game.msg.JsonMsg;
import com.game.msg.ProtoMsg;
import lombok.Data;

@Data
public class ProtoMsgTask implements Runnable{

    private ProtoMsg msg;

    @Override
    public void run() {
        ProtoBaseHandler handler = ProtoHandlerFactory.getHandler(msg.getCode());
        Player player = PlayerManager.getInstance().getPlayerById(msg.getSession().getPid());
        handler.doAction(player,msg);
    }

    public static ProtoMsgTask createTask(){
        return new ProtoMsgTask();
    }

    public int getProcessorId(){
        return ProcessorId.SYSTEM_PROCESSOR;
    }
}
