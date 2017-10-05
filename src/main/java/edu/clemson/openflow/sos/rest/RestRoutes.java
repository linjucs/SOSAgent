package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.Utils;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
    @author Khayam Anjam kanjam@g.clemson.edu
 **/

public class RestRoutes {
    private Context context;

    public RestRoutes(Context context) {
        this.context = context;
    }

    public Restlet getRoutes() {
        Router router = new Router(context);
        router.attach(Utils.PathBuilder(Utils.HEALTH_PATH), HealthStatus.class);
        router.attach(Utils.PathBuilder(Utils.REQUEST_PATH), IncomingRequests.class);
        return router;
    }

}
