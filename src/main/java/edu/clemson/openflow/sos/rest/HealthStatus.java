package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.PrefsSetup;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.LoggerFactory;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 * Class to return the agent status to the controller. Such as How many no. of clients this
 * agent supports, underlying agent2agent technology it supports. Controller need to make decision
 * based on these matrics. socket technology being used i.e java host, netty etc
 * TODO: return no. of active client connections.
 * TODO: Write the post call to be able to config agent i.e. drivers & max supported clients
 */
public class HealthStatus extends ServerResource {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HealthStatus.class);

    @Override
    protected Representation get() throws ResourceException {
        PrefsSetup  prefs = new PrefsSetup();
        String prefsString = prefs.getPrefs();
        log.debug("Prefs are {}", prefsString);
        return new JsonRepresentation(prefsString);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        return super.post(entity);
    }
}
