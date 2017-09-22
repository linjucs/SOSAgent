package edu.clemson.openflow.sos.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
    @author Khayam Anjam    kanjam@g.clemson.edu
    This class loads default prefs. Incase there is no existing perf file, this will be used
    NOTE: There is one global perf file and there should be one per southbound/northbound drivers based on their requirements.
 **/
public class DefaultPrefs {
    private static Logger logger = Utils.formatLogger(DefaultPrefs.class.getName());

    public static void loadDefault() {
        if (!isPrefExist(Utils.PREF_NAME_STRING)) {
            Preferences pref = getPrefRoot().node(Utils.PREF_NAME_STRING);
            pref.put(Utils.AGENT_DRIVER_STRING, Utils.DEFAULT_AGENT_DRIVER);
            logger.log(Level.INFO, "Created default preferences");
        }

    }

    public static void savePrefs() {

    }

    public static String getPref(String prefName, String prefKey) {
        if (isPrefExist(prefName)) {
            Preferences pref = getPrefRoot().node(Utils.PREF_NAME_STRING);
            return pref.get(prefKey, null);
        }
        else {
            logger.log(Level.WARNING, "{0} pref file doesn't exist", prefName);
            return null;
        }
    }
    private static boolean isPrefExist(String prefName) {
        try {
            return Preferences.userRoot().nodeExists(prefName);
        } catch (BackingStoreException e) {
            logger.log(Level.SEVERE, "Error checking if preferences exist ");
            e.printStackTrace();
            return false;
        }
    }

    private static Preferences getPrefRoot() {
        return Preferences.userRoot();
    }
}
