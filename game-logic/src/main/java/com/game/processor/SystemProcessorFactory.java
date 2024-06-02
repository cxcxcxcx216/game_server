package com.game.processor;

import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SystemProcessorFactory {

    private static final SystemProcessorFactory INSTANCE = new SystemProcessorFactory();

    private static final Map<Integer,IProcessor> PROCESSOR_MAP = new ConcurrentHashMap<>();

    static {

        PROCESSOR_MAP.put(ProcessorId.DEFAULT_PROCESSOR,new DefaultProcessor());
        PROCESSOR_MAP.put(ProcessorId.SYSTEM_PROCESSOR,new SystemProcessor());

    }


    public static IProcessor getProcessor(int type){
        IProcessor processor = PROCESSOR_MAP.get(type);
        if(processor==null){
            log.error("processor is null , {}",type);
        }
        return processor;
    }
}

