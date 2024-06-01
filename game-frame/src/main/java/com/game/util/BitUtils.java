package com.game.util;

public class BitUtils {

    //大小端转换
    public static short SwapInt16(short n) {
        return (short) (((n & 0xff) << 8) | ((n >> 8) & 0xff));
    }

    public static int SwapInt32(int n)
    {
        return ((n & 0xff) << 24) | (((n >> 8) & 0xff) << 16) | ((n >> 16) & 0xff << 8) | ((n >> 24) & 0xff);
    }

    public static long SwapInt64(long n)
    {
        return ((n&0xff) << 56) |
                (((n >> 8) & 0xff) << 48)|
                (((n >> 16) & 0xff) << 40)|
                (((n >> 24) & 0xff) << 32)|
                (((n >> 32) & 0xff) << 24|
                (((n >> 40) & 0xff) << 16)|
                (((n >> 48) & 0xff) << 8)|
                (((n >> 56) & 0xff)));
    }
}
