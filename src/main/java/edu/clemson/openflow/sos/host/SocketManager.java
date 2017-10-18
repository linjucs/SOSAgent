package edu.clemson.openflow.sos.host;

import edu.clemson.openflow.sos.host.netty.HostServer;
import edu.clemson.openflow.sos.rest.RequestParser;
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
       // if (request.isClientAgent()) {
            return startClientChannel(request);
      //  }
       // return true;
    }

    private boolean startClientChannel(RequestParser request) {

        HostServer hostServer = new HostServer(request);
        return hostServer.start();
    }

}