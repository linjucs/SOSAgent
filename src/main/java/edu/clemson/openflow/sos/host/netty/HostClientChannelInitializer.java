package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.agent.netty.AgentClientChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class HostClientChannelInitializer extends ChannelInitializer {

    private Channel remoteChannel;
    public HostClientChannelInitializer(Channel remoteChannel){
        this.remoteChannel = remoteChannel;
    }
    @Override
    protected void initChannel(Channel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast("bytesDecoder", new ByteArrayDecoder())
                .addLast("hostClient", new HostClientChannelHandler(remoteChannel))
                .addLast("bytesEncoder", new ByteArrayEncoder());
    }
}
