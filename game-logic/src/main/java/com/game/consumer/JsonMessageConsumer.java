package com.game.consumer;

import com.alibaba.fastjson.JSON;
import com.game.cache.PlayerManager;
import com.game.cache.SessionManager;
import com.game.entity.Player;
import com.game.msg.JsonMsg;
import com.game.net.Session;
import com.game.processor.ProcessorFactory;
import com.game.util.IDUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;


@Slf4j
public class JsonMessageConsumer implements Consumer{
    @Override
    public void consume(ChannelHandlerContext ctx, Object msg) {
        log.info("服务器收到消息");
        JsonMsg jsonMsg = (JsonMsg) msg;
        String string = Base64.getEncoder().encodeToString(jsonMsg.getData());
        jsonMsg.setBody(JSON.parse(string));
        JsonMsgTask task = JsonMsgTask.createTask();
        task.setMsg(jsonMsg);
        ProcessorFactory.getProcessor(task.getProcessorId()).execute(task);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("服务器收到连接 = {}",ctx.channel().remoteAddress());
        Session session = new Session(IDUtil.generateSnowflakeId(), ctx.channel());
        Player player = new Player();
        player.setSession(session);
        PlayerManager.getInstance().createPlayer(player);
        SessionManager.getInstance().addSession(session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("ip = {} ,is closed",ctx.channel().remoteAddress());
        SessionManager.getInstance().removeSession(ctx.channel());
        ctx.channel();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("channel is close, reason = {}",cause.toString());
        SessionManager.getInstance().removeSession(ctx.channel());
        ctx.channel();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }
}