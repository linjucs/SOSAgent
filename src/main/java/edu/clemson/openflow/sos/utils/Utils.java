package edu.clemson.openflow.sos.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/*
    @author Khayam Anjam
    kanjam@g.clemson.edu
 */
public class Utils {
    public static final int SERVER_PORT = 8002;

    public static final String BASE_PATH = "/sos";
    public static final String API_VERSION = "/v1.0";
    public static final String HEALTH_PATH = "/health";
    public static final String REQUEST_PATH = "/request";

    public static final String HOSTNAME_STRING = "hostname";
    public static final String DEFAULT_HOSTNAME = "default_host_xxx";

    public static final String PREF_NAME_STRING = "default_pref";

    public static final String AGENT_DRIVER_STRING = "agent-driver";
    public static final String DEFAULT_AGENT_DRIVER = "ptcp";

    public static String PathBuilder( String path) {
        return BASE_PATH + API_VERSION + path;
    }

    public static Logger formatLogger(String className) {
        Logger logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);
        LogFormatter formatter = new LogFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
        return logger;
    }
}
