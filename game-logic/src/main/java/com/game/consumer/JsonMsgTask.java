package com.game.consumer;


import com.game.manager.PlayerManager;
import com.game.constant.ProcessorId;
import com.game.entity.Player;
import com.game.handler.json.JsonBaseHandler;
import com.game.handler.json.JsonHandlerFactory;
import com.game.task.Task;
import lombok.Data;

@Data
public class JsonMsgTask extends Task {


    @Override
    public void run() {

        JsonBaseHandler handler = JsonHandlerFactory.getHandler(jsonMsg.getCode());
        Player player = PlayerManager.getInstance().getPlayerById(jsonMsg.getSession().getId());
        handler.doAction(player,jsonMsg);
    }

    public static JsonMsgTask createTask(){
        return new JsonMsgTask();
    }

    public int getProcessorId(){
        return ProcessorId.SYSTEM_PROCESSOR;
    }

}
