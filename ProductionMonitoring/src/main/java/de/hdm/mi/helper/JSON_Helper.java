package de.hdm.mi.helper;

import org.json.JSONObject;

public class JSON_Helper {
    public static JSONObject createPositiveResponse(int requestNumber, int errorCode) {
        JSONObject jo = new JSONObject();
        jo.put("Result", (new JSONObject()).put("Number", requestNumber).put("Success", true).put("Code", errorCode));

        return jo;
    }

    public static JSONObject createNegativeResponse(int requestNumber, int errorCode) {
        JSONObject jo = new JSONObject();
        jo.put("Result", (new JSONObject()).put("Number", requestNumber).put("Success", false).put("Code", errorCode));

        return jo;
    }
}
