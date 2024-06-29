package com.game.system.room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {

    private static final Map<Long,Room> ROOM_MAP = new ConcurrentHashMap<>();

    private static final RoomManager INSTANCE = new RoomManager();

    private RoomManager(){}

    public static RoomManager getInstance(){
        return INSTANCE;
    }



}
