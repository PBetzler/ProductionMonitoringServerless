package de.hdm.mi;


import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class MainClass_Tester {

    @Test
    public void handleRequestTest(){

        String expectedResponse = "{\"Result\":{\"Number\":1,\"Success\":true,\"Code\": 0}}";
        JSONObject jo = new JSONObject(expectedResponse);


        String returnString = (new MainClass()).handleRequest("{\"Request\":1,\"ProduktOK\":{\"ProduktID\":1}}", null);

        System.out.println(returnString);

        Assert.assertTrue(jo.toString().compareTo(returnString) == 0);
    }
}
