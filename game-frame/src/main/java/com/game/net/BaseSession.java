package com.game.net;

import io.netty.channel.ChannelHandlerContext;

public class BaseSession {

    protected long id;

    protected ChannelHandlerContext ctx;

    protected long createTime;

    protected long checkTime;


    public BaseSession() {
    }

    public BaseSession(long id, ChannelHandlerContext ctx, long createTime) {
        this.id = id;
        this.ctx = ctx;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }
}
