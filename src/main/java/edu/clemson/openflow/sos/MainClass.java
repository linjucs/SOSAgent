package edu.clemson.openflow.sos;

import edu.clemson.openflow.sos.rest.RestServer;
import edu.clemson.openflow.sos.utils.PrefsSetup;

/**
    @author Khayam Anjam    kanjam@g.clemson.edu
    This is the main entry point of the Agents. We will be starting all modules from here
 **/
public class MainClass {

    public static void main(String[] args) {
        PrefsSetup prefsSetup = new PrefsSetup();
        prefsSetup.loadDefault(); //load default settings

        try {
            RestServer restServer = new RestServer();
            restServer.startComponent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
