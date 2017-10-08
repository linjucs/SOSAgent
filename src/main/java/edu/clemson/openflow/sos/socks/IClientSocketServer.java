package edu.clemson.openflow.sos.socks;

public interface IClientSocketServer extends ISocketServer {
    /**
     *This method will return current active connections. Controller can use this in its decision
     * making process. Moreover you can also limit the max supported in your socket implementation
     * @return no. of active connections
     */
    int getActiveConnections();
}
