package com.game.constant;

import com.game.net.Session;
import io.netty.util.AttributeKey;

public interface GameConstant {

    interface Pkg{
        String PROCESSOR_PATH = "com.game";

        String HANDLER_PATH = "com.game";

    }

    interface Net{

        public static final AttributeKey<Session> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");
    }
}
