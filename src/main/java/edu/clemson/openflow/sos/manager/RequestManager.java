package edu.clemson.openflow.sos.manager;

import edu.clemson.openflow.sos.exceptions.RequestNotFoundException;
import edu.clemson.openflow.sos.rest.RequestParser;

import java.util.ArrayList;

public class RequestManager {
    private static ArrayList<RequestParser> incomingRequests = new ArrayList<>();

    public static void addToPool(RequestParser request) {
        incomingRequests.add(request);
    }

    public static ArrayList<RequestParser> getRequests() {
        return incomingRequests;
    }
    //TODO: define enum to differentiate between req from client and client-agents
    public static RequestParser getRequest(String IP, int port) throws RequestNotFoundException {
        for (RequestParser request : incomingRequests) {
            if ((request.getClientIP().equals(IP) &&
                    request.getClientPort() == port)  ||
                    request.getClientAgentIP().equals(IP))
                return request;
        }
        throw new RequestNotFoundException();
    }
}
