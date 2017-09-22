package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.DefaultPrefs;
import edu.clemson.openflow.sos.utils.Utils;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 */
public class HealthStatus extends ServerResource {
    private static Logger logger = Utils.formatLogger(HealthStatus.class.getName());

    @Override
    protected Representation get() throws ResourceException {
        JSONObject jsonResponse = new JSONObject()
                .put(Utils.HOSTNAME_STRING, getHostName())
                .put(Utils.AGENT_DRIVER_STRING, getAgentDriver());
        setStatus(Status.SUCCESS_ACCEPTED);
        return new JsonRepresentation(jsonResponse);
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.log(Level.WARNING, "Error while getting hostname, returning default {0}", e.getMessage());
            return Utils.DEFAULT_HOSTNAME;
        }

    }

    private String getAgentDriver() {
        String agentDriver = DefaultPrefs.getPref(Utils.PREF_NAME_STRING,
                Utils.AGENT_DRIVER_STRING);
        if (agentDriver != null) {
            logger.log(Level.WARNING, "{0} doesn't found in pref file, returning default",
                    Utils.PREF_NAME_STRING);
            return Utils.DEFAULT_AGENT_DRIVER;
        } else return Utils.DEFAULT_AGENT_DRIVER;

    }
}
