package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.host.RequestManager;
import edu.clemson.openflow.sos.host.SocketManager;
import edu.clemson.openflow.sos.rest.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 *  * TODO: Before connecting to client, compare its IP address with the one we are getting in requestParser Obj

 */
public class HostServerChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(HostServerChannelHandler.class);

    public HostServerChannelHandler() {
    }

    private RequestParser getRequest(String IP, int port) { // search the request manager pool to find current request
        for (RequestParser request : RequestManager.incomingRequests) {
            if (request.getClientIP().equals(IP) &&
                    request.getClientPort() == port) return request;
            else return null;
        }
        throw  new NullPointerException();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("Got Message from {} at Port {}",
                socketAddress.getHostName(),
                socketAddress.getPort());

        // TODO: Validate this host

        ByteBuf m = (ByteBuf) msg;
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
