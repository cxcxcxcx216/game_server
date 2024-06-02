package com.game.net.handler;

import com.alibaba.fastjson.JSON;
import com.game.constant.ProtocolConstants;
import com.game.msg.JsonMsg;
import com.game.msg.ProtoMsg;
import com.game.util.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JsonMessageEncoder extends MessageToByteEncoder<JsonMsg> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, JsonMsg msg, ByteBuf out) {

        short messageId = (short) msg.getCode();
        short headFlag = msg.getHead();
        byte[] data = JSON.toJSONString(msg.getBody()).getBytes();
        short length = (short) ((data == null ? 0 : (short) data.length) + ProtocolConstants.HEAD_LENGTH);

        out.writeShort(BitUtils.SwapInt16(length));
        out.writeShort(BitUtils.SwapInt16(messageId));
        out.writeShort(headFlag);
        out.writeBytes(data);
    }
}
