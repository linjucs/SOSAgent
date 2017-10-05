package edu.clemson.openflow.sos.socks;

import edu.clemson.openflow.sos.rest.RequestParser;

/**
 * @author Khayam Gondal kanjam@g.clemson.edu
 */
public interface ISocketServer {
    /*
    This method should return true if socket server is running. Incase of a error false should be returned.
    This method returns the result to http call from floodlight.
     */
    boolean start(RequestParser requestParser);
}
