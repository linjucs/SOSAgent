package edu.clemson.openflow.sos;

import edu.clemson.openflow.sos.rest.RestServer;
import edu.clemson.openflow.sos.utils.DefaultPrefs;
import edu.clemson.openflow.sos.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
    @author Khayam Anjam    kanjam@g.clemson.edu
    This is the main entry point of the Agents. We will be starting all modules from here
 **/
public class MainClass {

    public static void main(String[] args) {
        DefaultPrefs.loadDefault(); //load default settings
        RestServer restServer = new RestServer();
        try {
            restServer.startComponent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
