package com.duyj2.work.netty.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by LG on 2017/11/22.
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final int length = byteBuf.readableBytes();
        final byte[] b = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), b, 0, length);
        MessagePack mp = new MessagePack();
        list.add(mp.read(b));
    }
}
