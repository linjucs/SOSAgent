package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.Utils;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 */
public class RestServer {
    private Component component;
    private static Logger logger = Utils.formatLogger(RestServer.class.getName());


    public RestServer() {
        component = new Component();
        Server server = new Server(Protocol.HTTP, Utils.SERVER_PORT);
        component.getServers().add(server);
        component.getDefaultHost().attach(getRoutes());
    }

    private Restlet getRoutes() {
        Routes routes = new Routes(component.getContext().createChildContext());
        return routes.getRoutes();
    }

    public void startComponent() throws Exception {
        component.start();
        logger.log(Level.INFO, "Server Started on Port {0}", Utils.SERVER_PORT);

    }

    public void stopComponent() throws Exception {
        component.stop();
    }


}