package com.game.processor;

import com.game.annotation.Action;
import com.game.annotation.Processor;
import com.game.constant.GameConstant;
import com.game.handler.proto.BaseAction;
import com.game.processor.common.IProcessor;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class MsgProcessorFactory {


    private static final MsgProcessorFactory INSTANCE = new MsgProcessorFactory();

    private static final Map<Integer, Integer> MSG_PROCESSOR_MAP = new ConcurrentHashMap<>();

    public static MsgProcessorFactory getInstance(){
        return INSTANCE;
    }

    public static void register(int msgId,int processor){
        MSG_PROCESSOR_MAP.putIfAbsent(msgId,processor);
    }

    public static void init(){
        Set<Class<?>> classes = PackageScanner.scan(GameConstant.Pkg.HANDLER_PATH, Action.class);
        for (Class<?> clazz : classes) {
            Action action = clazz.getAnnotation(Action.class);
            int msgId = action.msgId();
            try {
                log.info("初始化消息 = {}",msgId);
                BaseAction instance =(BaseAction) clazz.newInstance();
                register(msgId,instance.getProcessor());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static IProcessor getProcessor(int msgId){
        int processorId = MSG_PROCESSOR_MAP.get(msgId);
        IProcessor processor = SystemProcessorFactory.getProcessor(processorId);
        if(processor==null){
            log.error("processor is null , {}",msgId);
        }
        return processor;
    }
}
