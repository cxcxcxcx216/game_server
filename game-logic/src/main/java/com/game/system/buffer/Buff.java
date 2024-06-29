package com.game.system.buffer;


import lombok.Data;

@Data
public class Buff {
    private int id;
    private int duration; // 持续时间（秒）
    private boolean stackable; // 是否可叠加
    private long startTime; // buff 开始时间

    public Buff(int id, String name, int duration, boolean stackable) {
        this.id = id;
        this.duration = duration;
        this.stackable = stackable;
        this.startTime = System.currentTimeMillis(); // 记录 buff 的开始时间
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isStackable() {
        return stackable;
    }

    // 更新 buff 的剩余持续时间
    public void updateDuration() {
        long now = System.currentTimeMillis();
        long elapsedSeconds = (now - startTime) / 1000;
        duration -= elapsedSeconds;
        startTime = now;
    }

    //执行buff
    public void exe(){

    }
}
