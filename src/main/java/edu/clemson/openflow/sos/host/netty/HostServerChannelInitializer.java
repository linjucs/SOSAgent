package edu.clemson.openflow.sos.host.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class HostServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //socketChannel.pipeline().addLast("frameDecoder",
        //        new LengthFieldBasedFrameDecoder(1195725860, 0,
        //                4, 0, 4));
        socketChannel.pipeline().addLast("bytesDecoder",
                new ByteArrayDecoder());
        socketChannel.pipeline().addLast("hostHandler", new HostServerChannelHandler());

        // Encoder
        //socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
        socketChannel.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());
    }
}
