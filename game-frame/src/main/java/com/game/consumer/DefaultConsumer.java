package com.game.consumer;

import com.game.handler.BaseHandler;
import com.game.handler.HandlerFactory;
import com.game.msg.ProtoMsg;
import com.game.processor.common.IProcessor;
import com.game.processor.common.ProcessorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DefaultConsumer implements Consumer{
    @Override
    public void consume(ChannelHandlerContext ctx, ProtoMsg msg) {
        log.info("ip = {}，消息={}",ctx.channel().remoteAddress().toString(),msg);
        //消费消息
        BaseHandler handler = HandlerFactory.getHandler(msg.getCode());
        if (handler == null)
            return;
        //封装消息
        Task task = Task.createTask();
        task.setMsg(msg);
        IProcessor processor = ProcessorFactory.getProcessor(handler.getProcessorId());
        processor.execute(task);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("{}链接成功",ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("{}断开链接",ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("{}异常断开链接，异常={}",ctx.channel().remoteAddress().toString(),cause.toString());
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        log.info("{} 触发事件，事件={}",ctx.channel().remoteAddress().toString(),evt.toString());
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }
}
