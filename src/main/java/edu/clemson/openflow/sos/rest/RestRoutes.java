package edu.clemson.openflow.sos.rest;

import edu.clemson.openflow.sos.utils.Utils;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 **/

public class RestRoutes {
    private Context context;
    protected static final String BASE_PATH = "/sos";
    protected static final String API_VERSION = "/v1.0";
    protected static final String HEALTH_PATH = "/health";
    protected static final String REQUEST_PATH = "/request";

    public RestRoutes(Context context) {
        this.context = context;
    }

    public Restlet getRoutes() {
        Router router = new Router(context);
        router.attach(PathBuilder(HEALTH_PATH), HealthStatus.class);
        router.attach(PathBuilder(REQUEST_PATH), FloodlightRequest.class);
        return router;
    }

    private static String PathBuilder(String path) {
        return BASE_PATH + API_VERSION + path;
    }
}
