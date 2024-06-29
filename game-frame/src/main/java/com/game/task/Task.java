package com.game.task;

import com.game.msg.JsonMsg;
import com.game.msg.ProtoMsg;
import lombok.Data;

@Data
public abstract class Task implements Runnable{

    protected ProtoMsg msg;

    protected JsonMsg jsonMsg;

}
