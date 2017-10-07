package edu.clemson.openflow.sos.socks.netty;

import com.sun.org.apache.regexp.internal.RE;
import edu.clemson.openflow.sos.rest.RequestParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 *  * TODO: Before connecting to client, compare its IP address with the one we are getting in requestParser Obj

 */
public class ClientSocketHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(ClientSocketHandler.class);
    private RequestParser request;

    public ClientSocketHandler(RequestParser request) {
        this.request = request;
        log.info("Expecting client IP {} Port {}",
                request.getClientIP(), request.getClientPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        String ipAddress = inetaddress.getHostAddress(); // IP address of client
        log.info("New Client connected IP {} Port {}", socketAddress.getHostName(), socketAddress.getPort());
    }
}
