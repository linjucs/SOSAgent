package edu.clemson.openflow.sos.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author Khayam Anjam    kanjam@g.clemson.edu
 * This class get & set the perferences and storing them in persistant storage **/
public class PrefsSetup {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PrefsSetup.class);
    private ObjectMapper mapper = new ObjectMapper();

    public static final String PREF_NAME = "agent_prefs";
    public void loadDefault() {
        if (!isPrefsExist(PREF_NAME)) {
            try {
                Preferences pref = getPrefRoot().node(PREF_NAME);
                String prefsToString = mapper.writeValueAsString(new PrefsParser());
                pref.put(PREF_NAME, prefsToString);
                log.info("Created default preferences {}", prefsToString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error("Error parsing default prefs for storing");

            }
        }

    }

    public void savePrefs(String prefs) {
        throw new NotImplementedException();
    }

    public String getPrefs() {
        if (isPrefsExist(PREF_NAME)) {
            Preferences pref = getPrefRoot().node(PREF_NAME);
            return pref.get(PREF_NAME, "");
        } else {
            log.warn("{} pref file doesn't exist", PREF_NAME);

            return null;
        }
    }

    private boolean isPrefsExist(String prefName) {
        try {
            return Preferences.userRoot().nodeExists(prefName);
        } catch (BackingStoreException e) {
            log.error("Error checking if preferences exist ");
            e.printStackTrace();
            return false;
        }
    }

    private static Preferences getPrefRoot() {
        return Preferences.userRoot();
    }
}
