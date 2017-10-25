package edu.clemson.openflow.sos.host.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HostClientChannelHandler extends ChannelInboundHandlerAdapter {

    private Channel remoteChannel;

    public HostClientChannelHandler(Channel remoteChannel) {
        this.remoteChannel = remoteChannel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        remoteChannel.writeAndFlush(msg);
    }
}
