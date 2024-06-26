package com.game.processor.factory;

import com.game.annotation.Processor;
import com.game.constant.GameConstant;
import com.game.processor.common.IProcessor;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SystemProcessorFactory {

    private static final SystemProcessorFactory INSTANCE = new SystemProcessorFactory();

    private static final Map<Integer,IProcessor> PROCESSOR_MAP = new ConcurrentHashMap<>();

    public static SystemProcessorFactory getInstance(){
        return INSTANCE;
    }

    public static void init(){
        Set<Class<?>> classes = PackageScanner.scan(GameConstant.Pkg.PROCESSOR_PATH, Processor.class);
        for (Class<?> clazz : classes) {
            Processor processor = clazz.getAnnotation(Processor.class);
            int processorId = processor.processorId();
            try {
                log.info("初始化处理器 = {}",processorId);
                IProcessor instance =(IProcessor) clazz.newInstance();
                if (PROCESSOR_MAP.get(processorId) != null) {
                    log.error("processor = {} 重复注册",processorId);
                }else {
                    PROCESSOR_MAP.put(processorId,instance);
                    log.error("processor = {} 注册成功",processorId);
                    instance.init();//初始化
                }

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

    public static void close(){
        for (Map.Entry<Integer, IProcessor> entry : PROCESSOR_MAP.entrySet()) {
            log.info("关闭处理器，ProcessorId = {}",entry.getKey());
            entry.getValue().shoutDown();
        }
        PROCESSOR_MAP.clear();

    }
}

