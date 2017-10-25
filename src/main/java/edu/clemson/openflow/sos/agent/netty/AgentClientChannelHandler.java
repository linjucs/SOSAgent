package edu.clemson.openflow.sos.agent.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AgentClientChannelHandler extends ChannelInboundHandlerAdapter {

    private Channel remoteChannel;

    public AgentClientChannelHandler(Channel remoteChannel) {
        this.remoteChannel = remoteChannel;
    }

    /**
     * Read from the server and write back using @param ctx
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    //write back the received message to remote channel.
        remoteChannel.writeAndFlush(msg);
    }
}
