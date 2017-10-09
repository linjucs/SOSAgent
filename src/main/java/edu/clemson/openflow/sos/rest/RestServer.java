package edu.clemson.openflow.sos.rest;

import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.slf4j.LoggerFactory;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 */
public class RestServer {
    private Component component;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RestServer.class);
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
        //Utils.formatLogger(RestServer.class.getName());
        log.info("Server started on port {}", REST_SERVER_PORT);
        component.start();

    }

    public void stopComponent() throws Exception {
        component.stop();
    }


}