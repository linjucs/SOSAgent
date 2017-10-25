package edu.clemson.openflow.sos.manager;

import edu.clemson.openflow.sos.exceptions.RequestNotFoundException;
import edu.clemson.openflow.sos.rest.RequestParser;

import java.util.ArrayList;

public enum RequestManager {
    INSTANCE;
    private ArrayList<RequestParser> incomingRequests = new ArrayList<>();

    public void addToPool(RequestParser request) {
        incomingRequests.add(request);
    }

    public ArrayList<RequestParser> getRequests() {
        return incomingRequests;
    }

    public RequestParser getRequest(String IP, int port) throws RequestNotFoundException {
        for (RequestParser request : incomingRequests) {
            if ((request.getClientIP().equals(IP) &&
                    request.getClientPort() == port)  ||
                    request.getClientAgentIP().equals(IP))
                return request;
        }
        throw new RequestNotFoundException();
    }
}
