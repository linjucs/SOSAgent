package edu.clemson.openflow.sos.manager;

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
        // check all the logistics before adding request to pool i.e is the server running or not
        // for now I am adding all requests to pool
        RequestManager.addToPool(request);
        log.debug("Added {} to the Request Pool", request.toString()); // need to override tostring yet
        return true;
    }


}