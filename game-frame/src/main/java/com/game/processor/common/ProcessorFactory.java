package com.game.processor.common;

import com.game.handler.BaseHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProcessorFactory {

    private static final ProcessorFactory INSTANCE = new ProcessorFactory();

    public static ProcessorFactory getInstance(){
        return INSTANCE;
    }

    private static final Map<Integer , IProcessor> PROCESSOR_MAP = new HashMap<>();


    public IProcessor getProcessor(int type){
        IProcessor processor = PROCESSOR_MAP.get(type);
        if(processor==null){
            log.error("processor is null , {}",type);
        }
        return processor;
    }
}

