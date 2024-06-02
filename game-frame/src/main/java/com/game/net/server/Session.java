package com.game.net.server;

import io.netty.channel.Channel;
import lombok.Data;

@Data
public class Session {

    private long pid;

    private Channel channel;

}
