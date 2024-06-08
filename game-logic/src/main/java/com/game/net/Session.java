package com.game.net;

import com.game.entity.Player;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private long id;

    private Channel channel;

    private long createTime;

    private Player player;
}
