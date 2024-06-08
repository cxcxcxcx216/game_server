package com.game.net;


import com.game.entity.Player;
import com.game.msg.ProtoMsg;
import com.game.util.BitUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.Builder;
import lombok.Data;

@Data
public class Session extends BaseSession{
    private Player player;

    public Session(){

    }

    public Session(Player player) {
        this.player = player;
    }

    public Session(long id, ChannelHandlerContext ctx, long createTime, Player player) {
        super(id, ctx, createTime);
        this.player = player;
    }

    public Session(long id, ChannelHandlerContext ctx, long createTime) {
        this.ctx = ctx;
        this.id = id;
        this.createTime = createTime;
    }

    public void sendMessage(int msgId,byte[] data){
        ProtoMsg protoMsg = new ProtoMsg();
        protoMsg.setCode((short) msgId);
        protoMsg.setData(data);
        protoMsg.setHead((short) 0);

        super.getCtx().writeAndFlush(protoMsg);
    }
}
