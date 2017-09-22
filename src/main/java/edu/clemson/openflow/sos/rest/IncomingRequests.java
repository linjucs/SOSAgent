package edu.clemson.openflow.sos.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.clemson.openflow.sos.utils.DefaultPrefs;
import edu.clemson.openflow.sos.utils.LogFormatter;
import edu.clemson.openflow.sos.utils.Utils;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/***
 * @author Khayam Anjam kanjam@g.clemson.edu
 * This class will receive the requests from controller and will initiate a socket connection for each of the client.
 */
public class IncomingRequests extends ServerResource {
    ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = Utils.formatLogger(IncomingRequests.class.getName());

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        try {
            JSONObject request = new JsonRepresentation(entity).getJsonObject();
            RequestParser parsedObject = mapper.readValue(request.toString(), RequestParser.class);
            logger.log(Level.INFO, "New connection request from controller");


            Representation response = new StringRepresentation("Valid Request !");
            setStatus(Status.SUCCESS_ACCEPTED);
            return response;

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to Parse Incoming JSON data.");
            e.printStackTrace();
            Representation response = new StringRepresentation("Request data is not valid");
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return response;
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Failed to Receive HTTP request.");
            e.printStackTrace();
            Representation response = new StringRepresentation("Not a valid HTTP Request");
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return response;

        }

    }
}
