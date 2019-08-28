package de.hdm.mi;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

public class MainClass implements RequestHandler<JSONObject, JSONObject> {

    @Override
    public JSONObject handleRequest(JSONObject input, Context context) {
        context.getLogger().log("Input: " + input.toString() +"\n");

        ProductionMonitoring_Controller_Interface controller = new ProductionMonitoring_Controller_Class();
        return controller.handleRequest(input);
    }

}
