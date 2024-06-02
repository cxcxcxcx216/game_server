package com.game.msg;

import com.game.constant.ProcessorId;
import com.game.net.server.Session;
import lombok.Data;

import javax.annotation.processing.Processor;

@Data
public class JsonMsg {

    private short code;

    private short head;

    private byte[] data;

    private Object body;

    private Session session;

}
