package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.Utils;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import javax.rmi.CORBA.Util;

/**
    @author Khayam Anjam kanjam@g.clemson.edu
 **/

public class Routes {
    private Context context;

    public Routes(Context context) {
        this.context = context;
    }

    public Restlet getRoutes() {
        Router router = new Router(context);
        router.attach(Utils.PathBuilder(Utils.HEALTH_PATH), HealthStatus.class);
        router.attach(Utils.PathBuilder(Utils.REQUEST_PATH), IncomingRequests.class);
        return router;
    }

}
