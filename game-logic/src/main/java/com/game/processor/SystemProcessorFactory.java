package com.game.processor;

import com.game.annotation.Action;
import com.game.annotation.Processor;
import com.game.constant.ProcessorId;
import com.game.processor.common.IProcessor;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SystemProcessorFactory {

    private static final SystemProcessorFactory INSTANCE = new SystemProcessorFactory();

    private static final Map<Integer,IProcessor> PROCESSOR_MAP = new ConcurrentHashMap<>();


    public static void init(){
        Set<Class<?>> classes = PackageScanner.scan("com.game", Processor.class);
        for (Class<?> clazz : classes) {
            Processor processor = clazz.getAnnotation(Processor.class);
            int processorId = processor.processorId();
            try {
                log.info("初始化处理器 = {}",processorId);
                IProcessor instance =(IProcessor) clazz.newInstance();
                PROCESSOR_MAP.put(processorId,instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static IProcessor getProcessor(int type){
        IProcessor processor = PROCESSOR_MAP.get(type);
        if(processor==null){
            log.error("processor is null , {}",type);
        }
        return processor;
    }
}

