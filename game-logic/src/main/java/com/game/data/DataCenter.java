package com.game.data;

public class DataCenter {

    public static final DataCenter INSTANCE = new DataCenter();

    public static DataCenter getInstance(){
        return INSTANCE;
    }


}
