package edu.clemson.openflow.sos.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PrefsParser {
    @JsonProperty("agent-drivers")
    private String agentDrivers = "ptcp";
    @JsonProperty("client-drivers")
    private String clientDrivers = "netty";
    @JsonProperty("max-clients")
    private int maxClientsSupported = 5;

    public PrefsParser() {

    }

    public PrefsParser(String agentDrivers,
                       int maxClientsSupported,
                       String clientDrivers) {
        this.agentDrivers = agentDrivers;
        this.maxClientsSupported = maxClientsSupported;
        this.clientDrivers = clientDrivers;
    }


    public void setAgentDrivers(String agentDrivers) {
        this.agentDrivers = agentDrivers;
    }

    public void setClientDrivers(String clientDrivers) {
        this.clientDrivers = clientDrivers;
    }

    public void setMaxClientsSupported(int maxClientsSupported) {
        this.maxClientsSupported = maxClientsSupported;
    }

    public String getAgentDrivers() {
        return agentDrivers;
    }

    public String getClientDrivers() {
        return clientDrivers;
    }

    public int getMaxClientsSupported() {
        return maxClientsSupported;
    }
}
