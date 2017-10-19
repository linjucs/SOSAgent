package edu.clemson.openflow.sos.host.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HostServerChannelDecoder extends ByteToMessageDecoder {
    private static final int BYTES_TO_READ= 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(byteBuf.readBytes(BYTES_TO_READ));
    }
}
