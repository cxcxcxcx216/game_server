package com.game.cache;

import cn.hutool.core.map.CamelCaseLinkedMap;
import com.game.net.Session;
import io.netty.channel.Channel;

import java.util.Map;


public class SessionManager {

    private static final Map<Channel,Session> SESSION_MAP = new CamelCaseLinkedMap<>();

    private static final SessionManager INSTANCE = new SessionManager();

    public static SessionManager getInstance(){
        return INSTANCE;
    }


    public boolean addSession(Session session){
        Session res = SESSION_MAP.put(session.getChannel(), session);
        if(res != null){
            return true;
        }
        return false;
    }


    public boolean removeSession(Channel channel){
        Session session = SESSION_MAP.remove(channel);
        if(session != null){
            return true;
        }
        return false;
    }
}
