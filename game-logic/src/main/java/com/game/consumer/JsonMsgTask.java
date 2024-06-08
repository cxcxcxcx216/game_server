package com.game.consumer;


import com.game.cache.PlayerManager;
import com.game.constant.ProcessorId;
import com.game.entity.Player;
import com.game.handler.json.JsonBaseHandler;
import com.game.handler.json.JsonHandlerFactory;
import com.game.msg.JsonMsg;
import lombok.Data;

@Data
public class JsonMsgTask implements Runnable{


    private JsonMsg msg;

    @Override
    public void run() {

        JsonBaseHandler handler = JsonHandlerFactory.getHandler(msg.getCode());
        Player player = PlayerManager.getInstance().getPlayerById(msg.getSession().getId());
        handler.doAction(player,msg);
    }

    public static JsonMsgTask createTask(){
        return new JsonMsgTask();
    }

    public int getProcessorId(){
        return ProcessorId.SYSTEM_PROCESSOR;
    }

}
