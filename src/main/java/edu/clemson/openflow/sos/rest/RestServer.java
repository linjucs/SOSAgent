package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.Utils;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 */
public class RestServer {
    private Component component;
    private static Logger logger = Utils.formatLogger(RestServer.class.getName());
    protected static final int REST_SERVER_PORT = 8002;


    public RestServer() {
        component = new Component();
        Server server = new Server(Protocol.HTTP, REST_SERVER_PORT);
        component.getServers().add(server);
        component.getDefaultHost().attach(getRoutes());
    }

    private Restlet getRoutes() {
        RestRoutes restRoutes = new RestRoutes(component.getContext().createChildContext());
        return restRoutes.getRoutes();
    }

    public void startComponent() throws Exception {
        logger.log(Level.INFO, "Server started on port {0}", REST_SERVER_PORT);
        component.start();

    }

    public void stopComponent() throws Exception {
        component.stop();
    }


}