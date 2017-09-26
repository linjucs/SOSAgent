package edu.clemson.openflow.sos.host;

import edu.clemson.openflow.sos.utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 * This class will start a socket to accept connections from connected client
 */
public class ClientSideSocket {
    private String clientIP;
    private int clientPort;
    private static final int CLIENT_DATA_PORT = 9877;

    private static Logger logger = Utils.formatLogger(ClientSideSocket.class.getName());


    public ClientSideSocket(String clientIP, int clientPort) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
    }

    public boolean startClientSideSocket() { //For testing I am using Java sockets. need to shift to netty

        ServerSocket clientSideSocket = null;
        try {
            clientSideSocket = new ServerSocket(CLIENT_DATA_PORT);
            logger.log(Level.INFO, "Starting client-side socket server for Client {0} at Port {1}",
                    new Object[] {clientIP, CLIENT_DATA_PORT});
            Socket socket = clientSideSocket.accept();
            logger.log(Level.INFO, "connected to client {0}", socket.getInetAddress().getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }
}
