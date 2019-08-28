package de.hdm.mi.validator;

import de.hdm.mi.db.DB_Manager_Dummy;
import de.hdm.mi.helper.Product_Monitoring_ResultSet_Integer;
import org.junit.Assert;
import org.junit.Test;

public class Product_Validator_Tester {

    @Test
    public void validateProductNegativeTest1(){
        Product_Validator_Class validator = new Product_Validator_Class(new DB_Manager_Dummy());
        Product_Monitoring_ResultSet_Integer result = validator.validateProduct(-1);

        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void validateProductNegativeTest2(){
        Product_Validator_Class validator = new Product_Validator_Class(new DB_Manager_Dummy());
        Product_Monitoring_ResultSet_Integer result = validator.validateProduct(24);

        Assert.assertFalse(result.getIfResultOk());
    }

    @Test
    public void validateProductPositiveTest1(){
        Product_Validator_Class validator = new Product_Validator_Class(new DB_Manager_Dummy());
        Product_Monitoring_ResultSet_Integer result = validator.validateProduct(5);

        Assert.assertTrue(result.getIfResultOk());
    }


}
