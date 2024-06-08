package com.game.handler;

import com.game.annotation.Action;
import com.game.constant.GameConstant;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Slf4j
public class HandlerFactory {

    private static final HandlerFactory INSTANCE = new HandlerFactory();

    private static final Map<Integer,BaseHandler> HANDLER_MAP = new HashMap<>();

    public static HandlerFactory getInstance(){
        return INSTANCE;
    }

    public static void init(){
        Set<Class<?>> classes = PackageScanner.scan(GameConstant.Pkg.HANDLER_PATH, Action.class);
        for (Class<?> clazz : classes) {
            Action action = clazz.getAnnotation(Action.class);
            int msgId = action.msgId();

            try {
                BaseHandler handler = (BaseHandler) clazz.newInstance();
                HANDLER_MAP.put(msgId, handler);
                log.info("添加handler = {}",msgId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static BaseHandler getHandler(int type){
        BaseHandler handler = HANDLER_MAP.get(type);
        if(handler == null){
            log.error("handler is null , type = {}", type);
        }
        return handler;
    }

}
