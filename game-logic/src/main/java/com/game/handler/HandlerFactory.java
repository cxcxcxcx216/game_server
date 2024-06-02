package com.game.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class HandlerFactory {

    private static final HandlerFactory INSTANCE = new HandlerFactory();

    private static final Map<Integer,BaseHandler> HANDLER_MAP = new HashMap<>();

    public static BaseHandler getHandler(int type){
        BaseHandler handler = HANDLER_MAP.get(type);
        if(handler == null){
            log.error("handler is null , type = {}", type);
        }
        return handler;
    }

}
