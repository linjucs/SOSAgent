package edu.clemson.openflow.sos.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PrefsParser {
    @JsonProperty("agent-drivers")
    private String agentDrivers = "ptcp";
    @JsonProperty("client-sock-type")
    private String clientSockType = "netty";
    @JsonProperty("agent-sock-type")
    private String agentSockType = "netty";
    @JsonProperty("max-clients")
    private int maxClientsSupported = 5;

    public PrefsParser() {

    }

    public String getAgentDrivers() {
        return agentDrivers;
    }

    public String getClientSockType() {
        return clientSockType;
    }

    public String getAgentSockType() {
        return agentSockType;
    }

    public int getMaxClientsSupported() {
        return maxClientsSupported;
    }
}