package edu.clemson.openflow.sos.host;

import edu.clemson.openflow.sos.rest.RequestParser;

/**
 * @author For each incoming request from controller, this class will be responsible for starting
 * i -> socket to client
 * ii -> socket to agent on the other side.
 */
public class SocketManager {

    public boolean socketRequest(RequestParser request) {
        if(request.isClientAgent()) { //means Its a request for client
            ClientSideSocket clientSideSocket = new ClientSideSocket(request.getClientIP(), request.getClientPort());
            clientSideSocket.startClientSideSocket();
        }
        return true;
    }
}
