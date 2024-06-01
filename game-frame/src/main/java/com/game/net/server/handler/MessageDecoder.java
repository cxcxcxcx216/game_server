package com.game.net.server.handler;

import com.game.constant.ProtocolConstants;
import com.game.msg.ProtoMsg;
import com.game.util.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> out) throws Exception {
        final int readableBytes = msg.readableBytes();
        if (readableBytes < ProtocolConstants.BASE_LENGTH) {
            return;
        }

        msg.markReaderIndex();
        int dataLength = BitUtils.SwapInt16(msg.readShort());//消息体长度

        dataLength = dataLength < 0 ? (dataLength + 65536):dataLength;
        if (!msg.isReadable(dataLength)) {//数据不够长，重置位置
            msg.resetReaderIndex();
            return;
        }

        short messageId = BitUtils.SwapInt16(msg.readShort());//消息Id
        short headFlag = msg.readShort();//压缩

        byte[] body = new byte[dataLength - ProtocolConstants.HEAD_LENGTH];
        msg.readBytes(body, 0, body.length);
        ProtoMsg protoMsg = new ProtoMsg();
        protoMsg.setCode(messageId);
        protoMsg.setHead(headFlag);
        protoMsg.setData(body);
        out.add(protoMsg);
    }
}
