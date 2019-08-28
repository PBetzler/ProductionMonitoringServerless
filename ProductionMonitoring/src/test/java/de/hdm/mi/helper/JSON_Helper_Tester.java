package de.hdm.mi.helper;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JSON_Helper_Tester {

    //There are no negative result tests possible. It has to work always!

    @Test
    public void createPositiveResponsePositiveTest1() {
        int requestNumber = 1;
        int errorCode = 0;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:true,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createPositiveResponse(requestNumber,errorCode).toString());
    }

    @Test
    public void createPositiveResponsePositiveTest2() {
        int requestNumber = -1;
        int errorCode = 1;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:true,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createPositiveResponse(requestNumber,errorCode).toString());
    }

    @Test
    public void createPositiveResponsePositiveTest3() {
        int requestNumber = -99;
        int errorCode = 10;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:true,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createPositiveResponse(requestNumber,errorCode).toString());
    }

    @Test
    public void createNegativeResponsePositiveTest1() {
        int requestNumber = 1;
        int errorCode = 0;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:false,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createNegativeResponse(requestNumber,errorCode).toString());
    }

    @Test
    public void createNegativeResponsePositiveTest2() {
        int requestNumber = -1;
        int errorCode = 1;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:false,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createNegativeResponse(requestNumber,errorCode).toString());
    }

    @Test
    public void createNegativeResponsePositiveTest3() {
        int requestNumber = -99;
        int errorCode = 10;
        String preUsedString = "{Result:{Number:" +Integer.toString(requestNumber) +",Success:false,Code: " +Integer.toString(errorCode) +"}}";
        String expectedResult = new JSONObject(preUsedString).toString();
        Assert.assertEquals(expectedResult, JSON_Helper.createNegativeResponse(requestNumber,errorCode).toString());
    }
}
