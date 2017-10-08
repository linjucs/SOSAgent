package edu.clemson.openflow.sos.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/*
    @author Khayam Anjam
    kanjam@g.clemson.edu
 */
public class Utils {

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
