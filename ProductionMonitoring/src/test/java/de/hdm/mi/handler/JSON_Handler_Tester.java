package de.hdm.mi.handler;

import de.hdm.mi.db.DB_Manager_Dummy;
import de.hdm.mi.helper.JSON_Helper;
import de.hdm.mi.helper.Product_Monitoring_ErrorCode;
import de.hdm.mi.validator.Product_Validator_Class;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JSON_Handler_Tester {

    @Test
    public void handleRequestNegativeTest1(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(null);
        Assert.assertEquals(JSON_Helper.createNegativeResponse(-1, Product_Monitoring_ErrorCode.INTERNAL_ERROR.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest2(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject());
        Assert.assertEquals(JSON_Helper.createNegativeResponse(-1, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest3(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:3,Produkt:{ProduktTypID:1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(3, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest4(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:3,Produkt:{}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(3, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest5(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:3}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(3, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest6(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:3,Produkt:{ID: 3}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(3, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest7(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5},FertigungsSchritt:{ProduktID:5}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest8(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5},FertigungsSchritt:{Info: 19,TeilID:1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest9(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }


    @Test
    public void handleRequestNegativeTest10(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1},FertigungsSchritt:{ProduktID:5,Info: 19,TeilID:1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest11(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{FertigungsCounter:5},FertigungsSchritt:{ProduktID:5,Info: 19,TeilID:1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest12(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,FertigungsSchritt:{ProduktID:5,Info: 19,TeilID:1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(99, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }


    @Test
    public void handleRequestNegativeTest13(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:1,ProduktOK:{ProduktID:-1}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(1, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest14(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:1,ProduktOK:{ProduktID:24}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(1, Product_Monitoring_ErrorCode.VALIDATOR_CHECK_FAILED.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest15(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:1,ProduktOK:{}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(1, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest16(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:1,Random:{}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(1, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestNegativeTest17(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Random:{}}"));
        Assert.assertEquals(JSON_Helper.createNegativeResponse(-1, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue()).toString(), result.toString());
    }



    @Test
    public void handleRequestPositiveTest1(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:3,Produkt:{ProduktTypID:1,ID: 3}}"));
        Assert.assertEquals(JSON_Helper.createPositiveResponse(3, Product_Monitoring_ErrorCode.OK.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestPositiveTest2(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5},FertigungsSchritt:{ProduktID:5,Info: 19,TeilID:1}}"));
        Assert.assertEquals(JSON_Helper.createPositiveResponse(99, Product_Monitoring_ErrorCode.OK.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestPositiveTest3(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request:1,ProduktOK:{ProduktID:1}}"));
        Assert.assertEquals(JSON_Helper.createPositiveResponse(1, Product_Monitoring_ErrorCode.OK.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestPositiveTest4(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5},FertigungsSchritt:{ProduktID:5,Info: 19}}"));
        Assert.assertEquals(JSON_Helper.createPositiveResponse(99, Product_Monitoring_ErrorCode.OK.getValue()).toString(), result.toString());
    }

    @Test
    public void handleRequestPositiveTest5(){
        DB_Manager_Dummy dummy = new DB_Manager_Dummy();
        JSON_Handler_Class handler = new JSON_Handler_Class(new Product_Validator_Class(dummy), dummy);
        JSONObject result = handler.handleRequest(new JSONObject("{Request: 99,HardwareZustand:{HardwareID:1,FertigungsCounter:5},FertigungsSchritt:{ProduktID:5,Info: 19,TeilID:-1}}"));
        Assert.assertEquals(JSON_Helper.createPositiveResponse(99, Product_Monitoring_ErrorCode.OK.getValue()).toString(), result.toString());
    }




}
