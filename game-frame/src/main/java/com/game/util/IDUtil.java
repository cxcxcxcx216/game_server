package com.game.util;

public class IDUtil {
    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    /**
     * 生成唯一的ID，基于Snowflake算法
     * @return 唯一的ID
     */
    public synchronized static long generateSnowflakeId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & 4095;
            if (sequence == 0) {
                // 如果在相同的毫秒内序列号用完，则阻塞到下一个毫秒再生成
                timestamp = waitUntilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - 1420070400000L) << 22) | (sequence << 12) | (0 << 10); // 时间戳（41位） + 序列号（12位） + 机器ID（10位）
    }

    private static long waitUntilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
}
}
