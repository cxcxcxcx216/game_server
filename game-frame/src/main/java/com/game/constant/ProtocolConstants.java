package com.game.constant;

public class ProtocolConstants {

    public static final  int AKKA_HEAD_LENGTH = 2 + 4;
    public static final  int BASE_LENGTH = 2;
    public static final  short HEAD_LENGTH = 4;

    public static final  short INNER_HEAD_LENGTH = 8;

    public static final  byte HEAD_FLAG_ROUTER = 0x01;

    public static final  byte HEAD_FLAG_ZIP = 0x02;

    public static final  byte HEAD_FLAG_UID = 0x04;

    public static final  byte HEAD_FLAG_ENCRYPT = 0x08;

    public static final  byte PACKET_FLAG_ZIP = (byte) (1 << 7); // 网络包压缩表识(Zip方式
}
