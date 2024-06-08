package com.game.cache;


public class BagManager {

    private static final BagManager INSTANCE = new BagManager();

    public static BagManager getInstance(){
        return INSTANCE;
    }
}
