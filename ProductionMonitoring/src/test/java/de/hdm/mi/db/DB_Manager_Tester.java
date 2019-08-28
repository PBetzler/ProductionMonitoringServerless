package de.hdm.mi.db;

import de.hdm.mi.helper.Product_Monitoring_ResultSet_General;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DB_Manager_Tester {

    @Test
    public void insertColumnNegativeTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());
        Product_Monitoring_ResultSet_General result = db.insertColumn("", null);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void insertColumnNegativeTest2(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());
        Product_Monitoring_ResultSet_General result = db.insertColumn("table", null);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void insertColumnNegativeTest3(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());
        Product_Monitoring_ResultSet_General result = db.insertColumn("negative", Arrays.asList(new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer)));
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void insertColumnPositiveTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());
        Product_Monitoring_ResultSet_General result = db.insertColumn("table", Arrays.asList(new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer)));
        Assert.assertTrue(result.getIfResultOk());
    }

    @Test
    public void getDeviationAndUsedPartsOfPartWithIDNegativeTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.getDeviationAndUsedPartsOfPartWithID(-1);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void getDeviationAndUsedPartsOfPartWithIDNegativeTest2(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.getDeviationAndUsedPartsOfPartWithID(5);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void getIfPartIsListedAsOKNegativeTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.getIfPartIsListedAsOK(-1);
        Assert.assertFalse(result.getIfResultOk());
    }

    //Cant do a positive test, cause sql ResultSet is abstract
    //@Test
    public void getIfPartIsListedAsOKPositiveTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.getIfPartIsListedAsOK(1);
        Assert.assertTrue(result.getIfResultOk());
    }

    @Test
    public void markPartAsNegativeTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.markPartAs(true, -1);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void markPartAsNegativeTest2(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.markPartAs(false, -1);
        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void markPartAsPositiveTest1(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.markPartAs(true, 1);
        Assert.assertTrue(result.getIfResultOk());
    }
    @Test
    public void markPartAsPositiveTest2(){
        DB_Manager_Class db = new DB_Manager_Class(new DB_Connection_Dummy());

        Product_Monitoring_ResultSet_General result = db.markPartAs(false, 1);
        Assert.assertTrue(result.getIfResultOk());
    }
}
