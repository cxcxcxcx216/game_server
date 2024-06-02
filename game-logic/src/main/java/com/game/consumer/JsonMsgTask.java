package com.game.consumer;


import com.game.cache.PlayerManager;
import com.game.constant.ProcessorId;
import com.game.entity.Player;
import com.game.handler.BaseHandler;
import com.game.handler.HandlerFactory;
import com.game.msg.JsonMsg;
import lombok.Data;

@Data
public class JsonMsgTask implements Runnable{


    private JsonMsg msg;

    @Override
    public void run() {

        BaseHandler handler = HandlerFactory.getHandler(msg.getCode());
        Player player = PlayerManager.getInstance().getPlayerById(msg.getSession().getPid());
        handler.doAction(player,msg);
    }

    public static JsonMsgTask createTask(){
        return new JsonMsgTask();
    }

    public int getProcessorId(){
        return ProcessorId.SYSTEM_PROCESSOR;
    }

}
