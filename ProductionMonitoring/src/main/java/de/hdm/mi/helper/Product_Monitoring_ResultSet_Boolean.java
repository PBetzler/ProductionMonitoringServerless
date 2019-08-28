package de.hdm.mi.helper;

public class Product_Monitoring_ResultSet_Boolean extends Product_Monitoring_ResultSet_General {

    private boolean value;

    public Product_Monitoring_ResultSet_Boolean(boolean value, boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        super(resultOk, errorCode);
        this.value = value;
    }

    public Product_Monitoring_ResultSet_Boolean(boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        super(resultOk, errorCode);
        this.value = false;
    }

    public boolean getValue() {
        return value;
    }


}
