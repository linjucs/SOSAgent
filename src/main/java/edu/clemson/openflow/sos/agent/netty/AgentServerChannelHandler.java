package edu.clemson.openflow.sos.agent.netty;

import edu.clemson.openflow.sos.host.netty.HostClient;
import edu.clemson.openflow.sos.manager.RequestManager;
import edu.clemson.openflow.sos.rest.RequestParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class AgentServerChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(AgentServerChannelHandler.class);
    private RequestParser request;
    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("New agent-side connection from {} at Port {}",
                socketAddress.getHostName(),
                socketAddress.getPort());
        this.request = RequestManager.getRequest(socketAddress.getHostName(), socketAddress.getPort());
        HostClient hostClient = new HostClient(request.getServerIP(), request.getServerPort());

        hostClient.start();
        this.channel = hostClient.getChannel();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        if (channel != null) {
            log.info("received packet from other agent");
            //byte[] m = (byte[] ) msg;
            //log.info(new String(m));
            channel.writeAndFlush(msg); // will be moved to host client channel handler

        }
        else {
            log.error("Couldn't connect to server {}", request.getServerIP());
        }
        ReferenceCountUtil.release(msg);

    }
}
