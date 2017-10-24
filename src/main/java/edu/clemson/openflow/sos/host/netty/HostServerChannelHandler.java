package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.agent.netty.AgentClient;
import edu.clemson.openflow.sos.manager.RequestManager;
import edu.clemson.openflow.sos.rest.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 *  * TODO: Before connecting to client, compare its IP address with the one we are getting in requestParser Obj

 */
public class HostServerChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(HostServerChannelHandler.class);
    private RequestParser request;
    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("New host-side connection from {} at Port {}",
                socketAddress.getHostName(),
                socketAddress.getPort());
        this.request = RequestManager.getRequest(socketAddress.getHostName(), socketAddress.getPort());
        AgentClient agentClient = new AgentClient(request.getServerAgentIP());
        agentClient.start();
        this.channel = agentClient.getChannel();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (channel != null) {
            log.info("got packet from client");

            channel.writeAndFlush(msg); // will be moved to agent server channel handler

            }
            else {
            log.error("Couldn't connect to remote agent {}", request.getServerAgentIP());
        }
        ReferenceCountUtil.release(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    private void forwardMessage(ByteBuf msg) {

    }
}
