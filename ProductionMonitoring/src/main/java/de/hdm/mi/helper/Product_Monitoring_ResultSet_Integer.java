package de.hdm.mi.helper;

public class Product_Monitoring_ResultSet_Integer extends Product_Monitoring_ResultSet_General{
    private int value;


    public Product_Monitoring_ResultSet_Integer(int value, boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        super(resultOk, errorCode);
        this.value = value;
    }

    public Product_Monitoring_ResultSet_Integer(boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        this(-1, resultOk, errorCode);
    }

    public int getValue() {
        return value;
    }


}
