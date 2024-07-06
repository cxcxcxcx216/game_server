package com.game.listener;

import com.game.annotation.Listener;
import com.game.util.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class EventUtil {

    private final static Map<Integer, List<IListener>> LISTENER_MAP = new ConcurrentHashMap<>();

    public static void fire(int type,Object param){
        List<IListener> listeners = LISTENER_MAP.getOrDefault(type, null);
        if(listeners == null || listeners.size() == 0){
            log.error("lister is empty, listener = {}",type);
            return;
        }else {
            for (IListener listener : listeners) {
                listener.update(param);
            }
        }

    }

    /**
     * 注册监听器
     * @param type
     * @param listener
     */
    public static void register(int type,IListener listener){
        log.info("注册监听器， listener  = {}",type);
        List<IListener> listeners = LISTENER_MAP.getOrDefault(type, null);
        if(listeners != null){
            listeners.add(listener);
        }else{
            List<IListener> list = new ArrayList<>();
            list.add(listener);
            LISTENER_MAP.put(type, list);

        }

    }

    /**
     * 移除监听器
     * @param type
     */
    public static void remove(int type){
        LISTENER_MAP.remove(type);
    }

    public static void init(String path){
        Set<Class<?>> classes = PackageScanner.scan(path, Listener.class);
        for (Class<?> clazz : classes) {
            Listener listener = clazz.getAnnotation(Listener.class);
            int type = listener.type();
            try {
                register(type, (IListener) clazz.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }



}
