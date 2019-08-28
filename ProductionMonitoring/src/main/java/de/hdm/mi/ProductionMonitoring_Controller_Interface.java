package de.hdm.mi;

import org.json.JSONObject;

public interface ProductionMonitoring_Controller_Interface {
    public JSONObject handleRequest(JSONObject request);
    public JSONObject handleRequest(String request);
}
