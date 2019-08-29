package de.hdm.mi;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

public class MainClass implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input.toString() +"\n");

        ProductionMonitoring_Controller_Interface controller = new ProductionMonitoring_Controller_Class();
        return controller.handleRequest(input).toString();
    }

}
