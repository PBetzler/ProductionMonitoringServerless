package de.hdm.mi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ProductionMonitoring_Controller_Class extends ProductionMonitoring_Controller_Abstract {

    private static final Logger logger = LogManager.getLogger(ProductionMonitoring_Controller_Class.class);

    @Override
    public JSONObject handleRequest(JSONObject request) {
        int breakpoint;
        try {
            return handler.handleRequest(request);
        } catch (Exception e) {
            logger.fatal("Got an critical uncaught exception! Returning. Exception was: " +e.getMessage() +" Stacktrace was: " +e.getStackTrace());
            return new JSONObject();
        }

    }

    @Override
    public JSONObject handleRequest(String request) {
        JSONObject jo = new JSONObject(request);

        if (jo != null && !jo.isEmpty()) {
            return this.handleRequest(jo);
        }

        logger.error("Could not parse String: " +request +" to JSONObject!");
        return new JSONObject();
    }
}
