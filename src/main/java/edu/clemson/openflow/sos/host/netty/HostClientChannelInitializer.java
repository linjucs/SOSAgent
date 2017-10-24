package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.agent.netty.AgentClientChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class HostClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("bytesDecoder",
                new ByteArrayDecoder());
        socketChannel.pipeline().addLast("hostClient", new HostClientChannelHandler());

        // Encoder
        //socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
        socketChannel.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());
    }
}
