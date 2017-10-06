package edu.clemson.openflow.sos.socks;

import edu.clemson.openflow.sos.rest.RequestParser;
import edu.clemson.openflow.sos.socks.netty.NettyClientSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Khayam Gondal kanjam@g.clemson.edu
 *
 * Class to manage individual socket servers. i.e socket to handle sos-client, sos-server and
 * other sos-agents
 */
public class SocketManager {
    private static final Logger log = LoggerFactory.getLogger(SocketManager.class);


    public boolean socketRequest(RequestParser request) {
        if (request.isClientAgent()) {
            return startClientChannel(request);
        }
        return true;
    }

    private boolean startClientChannel(RequestParser request) {

        NettyClientSocket nettyClientSocket = new NettyClientSocket();
        return nettyClientSocket.start(request);
    }

}