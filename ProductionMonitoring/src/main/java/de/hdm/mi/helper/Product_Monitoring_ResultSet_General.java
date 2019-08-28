package de.hdm.mi.helper;

public class Product_Monitoring_ResultSet_General {
    private boolean resultOk;
    private Product_Monitoring_ErrorCode errorCode;

    public Product_Monitoring_ResultSet_General(boolean resultOk, Product_Monitoring_ErrorCode errorCode){
        this.resultOk = resultOk;
        this.errorCode = errorCode;
    }

    public boolean getIfResultOk() {
        return resultOk;
    }

    public Product_Monitoring_ErrorCode getErrorCode() {
        return errorCode;
    }
}
