package com.game.net;

import com.game.entity.Player;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private long id;

    private ChannelHandlerContext ctx;

    private long createTime;

    private Player player;
}
