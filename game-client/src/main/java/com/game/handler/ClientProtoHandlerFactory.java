package com.game.handler;

import com.game.annotation.Action;
import com.game.constant.GameConstant;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Slf4j
public class ClientProtoHandlerFactory {

    private static final ClientProtoHandlerFactory INSTANCE = new ClientProtoHandlerFactory();

    private static final Map<Integer, ProtoBaseHandler> HANDLER_MAP = new HashMap<>();

    public static ClientProtoHandlerFactory getInstance(){
        return INSTANCE;
    }

    public static void init(){
        Set<Class<?>> classes = PackageScanner.scan(GameConstant.Pkg.HANDLER_PATH, Action.class);
        for (Class<?> clazz : classes) {
            if (clazz.getSuperclass().equals(ProtoBaseHandler.class)){
                Action action = clazz.getAnnotation(Action.class);
                int msgId = action.msgId();
                try {
                    ProtoBaseHandler handler = (ProtoBaseHandler) clazz.newInstance();
                    HANDLER_MAP.put(msgId, handler);
                    log.info("添加handler = {}",msgId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static ProtoBaseHandler getHandler(int type){
        ProtoBaseHandler handler = HANDLER_MAP.get(type);
        if(handler == null){
            log.error("handler is null , type = {}", type);
        }
        return handler;
    }

}
